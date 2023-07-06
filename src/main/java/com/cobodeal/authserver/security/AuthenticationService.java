package com.cobodeal.authserver.security;

import com.cobodeal.authserver.constants.TokenType;
import com.cobodeal.authserver.dao.user.IUserDao;
import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.dto.Token;
import com.cobodeal.authserver.dto.User;
import com.cobodeal.authserver.requests.LoginRequest;
import com.cobodeal.authserver.requests.RegisterRequest;
import com.cobodeal.authserver.responses.AuthenticationResponse;
import com.cobodeal.authserver.responses.apiresponses.CustomFailureEnum;
import com.cobodeal.authserver.responses.apiresponses.CustomSuccessEnum;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.security.JwtService;
import com.cobodeal.authserver.security.TokenService;
import com.cobodeal.authserver.service.IEmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final IEmailService emailService;

    @Value("${auth.verify-url}")
    private String verifyUrl;

    public ResponseEntity<Response<String>> register(RegisterRequest request) {

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(Role.builder().roleId(request.getRole()).build())
                .password(passwordEncoder.encode(request.getPassword()))
                .verified(false)
                .build();
        String verificationToken = UUID.randomUUID().toString();
        try {

            userDao.saveUser(user);

            userDao.saveVerificationToken(user,verificationToken);

            emailService.sendEmail(request.getEmail(), "Email verification at Cobodeal", "Please click the following link to verify your email: ".concat(verifyUrl).concat("?token=").concat(verificationToken));

            return new ResponseEntity<Response<String>>(new Response<String>(CustomSuccessEnum.REGISTRATION_SUCCESS,user.getEmail()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Response<String>>(new Response<String>(CustomFailureEnum.REGISTRATION_FAILED,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Response<AuthenticationResponse>> login(LoginRequest request) throws AuthenticationException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userDao.findByEmail(request.getEmail())
                .orElseThrow();

        if(!user.getVerified()){
            throw new AuthenticationException("UnAuthorized");
        }
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new ResponseEntity<Response<AuthenticationResponse>>(
                new Response<AuthenticationResponse>(CustomSuccessEnum.LOGIN_SUCCESS,
                        AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .refreshToken(refreshToken)
                                .build()),HttpStatus.OK);
    }


    public ResponseEntity<Response<String>> verify(String token) throws AuthenticationException {

        var user = userDao.findByVerificationToken(token)
                .orElseThrow();

        if(userDao.verifyUser(user.getId())){
            return new ResponseEntity<Response<String>>(new Response<String>(CustomSuccessEnum.VERIFICATION_SUCCESS,user.getEmail()), HttpStatus.OK);
        }else {
            return new ResponseEntity<Response<String>>(new Response<String>(CustomFailureEnum.VERIFICATION_FAILED,user.getEmail()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userDao.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .userId(user.getId())
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenService.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenService.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenService.saveAll(validUserTokens);
    }
}
