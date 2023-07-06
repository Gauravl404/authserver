package com.cobodeal.authserver.service;

import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.responses.apiresponses.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRoleService {
    ResponseEntity<Response<List<Role>>> getAllRoles();

    ResponseEntity<Response<Role>> getRole(Integer roleId);

    ResponseEntity<Response<Role>> createRole(String name);

    ResponseEntity<Response<Role>> updateRole(Integer roleId, String name);

    ResponseEntity<Response<Boolean>> deleteRole(Integer roleId);
}
