package com.cobodeal.authserver.controllers;

import com.cobodeal.authserver.dto.Api;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/api")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ApiController {

    private final IApiService apiService;


    @GetMapping("/get_all_api")
    public ResponseEntity<Response<List<Api>>> getAllApi(){
        return apiService.getAllApis();
    }

    @GetMapping("/get_api_by_id/{api_id}")
    public ResponseEntity<Response<Api>> getApi(@PathVariable(value = "api_id") Integer apiId){
        return apiService.getApi(apiId);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<Api>> createApi(@RequestBody Api api){
        return apiService.createApi(api);
    }

    @PutMapping("/update/{api_id}")
    public ResponseEntity<Response<Api>> updateApi(@PathVariable(value = "api_id") Integer roleId , @RequestBody Api api ){
        return apiService.updateApi(api);
    }
    @DeleteMapping("/delete/{api_id}")
    public ResponseEntity<Response<Boolean>> deleteApi(@PathVariable(value = "api_id") Integer apiId){
        return apiService.deleteApi(apiId);
    }
}
