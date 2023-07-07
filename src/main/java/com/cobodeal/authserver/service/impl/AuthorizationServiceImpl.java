package com.cobodeal.authserver.service.impl;

import com.cobodeal.authserver.dao.role.IRolePermissionDao;
import com.cobodeal.authserver.responses.apiresponses.CustomFailureEnum;
import com.cobodeal.authserver.responses.apiresponses.CustomSuccessEnum;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.security.JwtService;
import com.cobodeal.authserver.service.IAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final JwtService jwtService;

    private final IRolePermissionDao rolePermissionDao;



    @Override
    public ResponseEntity<Response<Boolean>> authenticate(String token, String method, String endPoint) {

        String email = jwtService.extractUserName(token);

        var res = rolePermissionDao.checkAuthorizationForApi(email, method, endPoint);

        return res ? new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.API_AUTHORIZE_SUCCESS, true)
                , HttpStatus.OK)
                :
                new ResponseEntity<>(
                        new Response<>(
                                CustomFailureEnum.API_AUTHORIZE_FAILED, false)
                        , HttpStatus.FORBIDDEN);
    }
}
