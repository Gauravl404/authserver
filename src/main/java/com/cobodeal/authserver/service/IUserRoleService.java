package com.cobodeal.authserver.service;

import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.requests.AssignRoleRequest;
import com.cobodeal.authserver.responses.apiresponses.Response;
import org.springframework.http.ResponseEntity;

public interface IUserRoleService {
    ResponseEntity<Response<String>> assignRoleToUser(AssignRoleRequest request);

    ResponseEntity<Response<Role>> getRoleOfUser(Integer userId);
}
