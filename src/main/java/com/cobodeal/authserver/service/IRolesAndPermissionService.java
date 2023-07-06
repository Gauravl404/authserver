package com.cobodeal.authserver.service;

import com.cobodeal.authserver.requests.AddRolePermissionRequest;
import com.cobodeal.authserver.responses.RolePermissionsResponse;
import com.cobodeal.authserver.responses.apiresponses.Response;
import org.springframework.http.ResponseEntity;

public interface IRolesAndPermissionService {
    ResponseEntity<Response<String>> addRolePermissions(AddRolePermissionRequest request);

    ResponseEntity<Response<RolePermissionsResponse>> updateRolePermissions(AddRolePermissionRequest request);

    ResponseEntity<Response<String>> deleteRolePermissions(Integer roleId);

    ResponseEntity<Response<RolePermissionsResponse>> getRolePermissions(Integer roleId);
}
