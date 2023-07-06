package com.cobodeal.authserver.service.impl;

import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.requests.AssignRoleRequest;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IUserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Override
    public ResponseEntity<Response<String>> assignRoleToUser(AssignRoleRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Response<Role>> getRoleOfUser(Integer userId) {
        return null;
    }
}
