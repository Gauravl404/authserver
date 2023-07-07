package com.cobodeal.authserver.service;

import com.cobodeal.authserver.requests.RolePermissionRequest;
import com.cobodeal.authserver.responses.RolePermissionsResponse;
import com.cobodeal.authserver.responses.apiresponses.Response;
import org.springframework.http.ResponseEntity;

public interface IRolesAndPermissionService {
    ResponseEntity<Response<String>> addRolePermissions(RolePermissionRequest request);

    ResponseEntity<Response<Boolean>> updateRolePermissions(RolePermissionRequest request);

    ResponseEntity<Response<String>> deleteRolePermissions(Integer roleId);

    ResponseEntity<Response<RolePermissionsResponse>> getRolePermissions(Integer roleId);

    ResponseEntity<Response<String>> deleteMultipleRolePermissions(RolePermissionRequest request);
}
