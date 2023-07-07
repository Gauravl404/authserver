package com.cobodeal.authserver.dao.role;

import com.cobodeal.authserver.requests.RolePermissionRequest;
import com.cobodeal.authserver.responses.RolePermissionsResponse;

import java.util.List;
import java.util.Optional;

public interface IRolePermissionDao {
    Boolean addRolePermissions(RolePermissionRequest request);

    Boolean deleteRolePermissions(Integer roleId);

    Boolean deleteRolesMultiplePermissions(Integer roleId, List<Integer>  apiIds);

    Optional<RolePermissionsResponse> getRolePermissions(Integer roleId);


}
