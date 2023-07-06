package com.cobodeal.authserver.dao.role;

import com.cobodeal.authserver.dto.Api;
import com.cobodeal.authserver.dto.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleDao {

    List<Role> getAllRoles();
    Optional<Role> getRole(Integer id);
    Optional<Role> createRole(Role role);
    Optional<Role> updateRole(Role role);
    Boolean deleteRole(Integer roleId);
}
