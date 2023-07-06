package com.cobodeal.authserver.service.impl;

import com.cobodeal.authserver.requests.AddRolePermissionRequest;
import com.cobodeal.authserver.responses.RolePermissionsResponse;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IRolesAndPermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleAndPermissionServiceImpl implements IRolesAndPermissionService {
    @Override
    public ResponseEntity<Response<String>> addRolePermissions(AddRolePermissionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Response<RolePermissionsResponse>> updateRolePermissions(AddRolePermissionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Response<String>> deleteRolePermissions(Integer roleId) {
        return null;
    }

    @Override
    public ResponseEntity<Response<RolePermissionsResponse>> getRolePermissions(Integer roleId) {
        return null;
    }
}
