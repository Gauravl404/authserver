package com.cobodeal.authserver.dao.user;

import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.requests.AssignRoleRequest;

import java.util.Optional;

public interface IUserRoleDao {
    Boolean assignRoleToUser(AssignRoleRequest request);

    Optional<Role> getAssignedRoleToUser(Integer userId);
}
