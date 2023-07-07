package com.cobodeal.authserver.service;

import com.cobodeal.authserver.responses.apiresponses.Response;
import org.springframework.http.ResponseEntity;

public interface IAuthorizationService {
    ResponseEntity<Response<Boolean>> authenticate(String token, String method, String endPoint);
}
