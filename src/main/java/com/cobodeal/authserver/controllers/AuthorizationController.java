package com.cobodeal.authserver.controllers;

import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class AuthorizationController {

    private final IAuthorizationService authorizationService;

    @GetMapping("/get_authority")
    public ResponseEntity<Response<Boolean>> auth(@RequestHeader("Authorization") String token, @RequestParam("end_point") String endPoint,@RequestParam("method") String method){
        return authorizationService.authenticate(token,method,endPoint);
    }

}
