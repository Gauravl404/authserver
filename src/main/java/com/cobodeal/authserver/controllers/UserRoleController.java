package com.cobodeal.authserver.controllers;

import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.requests.AssignRoleRequest;
import com.cobodeal.authserver.requests.RegisterRequest;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserRoleController {

    private final IUserRoleService userRoleService;

    //assign or update a role to user
    @PostMapping("/assign_role")
    public ResponseEntity<Response<String>> assignRole(@RequestBody AssignRoleRequest request) {
        return userRoleService.assignRoleToUser(request);
    }

    //fetch role of a user
    @GetMapping("/get_role/{user_id}")
    public ResponseEntity<Response<Role>> assignRole(@PathVariable(value = "user_id") Integer userId) {
        return userRoleService.getRoleOfUser(userId);
    }

}
