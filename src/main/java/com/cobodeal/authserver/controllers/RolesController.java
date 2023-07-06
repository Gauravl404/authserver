package com.cobodeal.authserver.controllers;

import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/admin/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RolesController {

    private final IRoleService roleService;

    @GetMapping("/get_all_role")
    public ResponseEntity<Response<List<Role>>> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/get_role_by_id/{role_id}")
    public ResponseEntity<Response<Role>> getRole(@PathVariable(value = "role_id") Integer roleId){
        return roleService.getRole(roleId);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<Role>> createRole(@RequestBody HashMap<String,String> roles ){
        return roleService.createRole(roles.get("name"));
    }

    @PutMapping("/update/{role_id}")
    public ResponseEntity<Response<Role>> updateRole(@PathVariable(value = "role_id") Integer roleId , @RequestBody HashMap<String,String> roles ){
        return roleService.updateRole(roleId,roles.get("name"));
    }

    @DeleteMapping("/delete/{role_id}")
    public ResponseEntity<Response<Boolean>> deleteRole(@PathVariable(value = "role_id") Integer roleId){
        return roleService.deleteRole(roleId);
    }

}
