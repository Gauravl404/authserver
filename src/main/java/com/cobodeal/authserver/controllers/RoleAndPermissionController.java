package com.cobodeal.authserver.controllers;

import com.cobodeal.authserver.requests.AddRolePermissionRequest;
import com.cobodeal.authserver.responses.RolePermissionsResponse;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IRolesAndPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/role/permission")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoleAndPermissionController {

    private final IRolesAndPermissionService rolesAndPermissionService;
    @PostMapping("/add")
    public ResponseEntity<Response<String>> addAPIForRole(@RequestBody AddRolePermissionRequest request){
        return rolesAndPermissionService.addRolePermissions(request);
    }

    @PutMapping("/update")
    public ResponseEntity<Response<RolePermissionsResponse>> updateAPIForRole(@RequestBody AddRolePermissionRequest request){
        return rolesAndPermissionService.updateRolePermissions(request);
    }

    @DeleteMapping("/delete/{role_id}")
    public ResponseEntity<Response<String>> deleteAPIForRole(@PathVariable(value = "role_id") Integer roleId){
        return rolesAndPermissionService.deleteRolePermissions(roleId);
    }

    @GetMapping("/get/{role_id}")
    public ResponseEntity<Response<RolePermissionsResponse>> getAPIForRole(@PathVariable(value = "role_id") Integer roleId){
        return rolesAndPermissionService.getRolePermissions(roleId);
    }



}
