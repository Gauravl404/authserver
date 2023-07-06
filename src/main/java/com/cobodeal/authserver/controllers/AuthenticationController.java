package com.cobodeal.authserver.controllers;

import com.cobodeal.authserver.requests.LoginRequest;
import com.cobodeal.authserver.requests.RegisterRequest;
import com.cobodeal.authserver.responses.AuthenticationResponse;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.security.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<Response<String>> register(
            @RequestBody RegisterRequest request
    ) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<AuthenticationResponse>> login(
            @RequestBody LoginRequest request
    ) throws AuthenticationException {
        return authenticationService.login(request);
    }


    @GetMapping("/verify-email")
    public ResponseEntity<Response<String>> verify(
            @RequestParam("token") String verificationToken
    ) throws AuthenticationException {
        return authenticationService.verify(verificationToken);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }


}
