package com.cobodeal.authserver.responses;

import com.cobodeal.authserver.dto.Api;
import com.cobodeal.authserver.dto.Role;

import java.util.List;

public class RolePermissionsResponse {
    private Role role;
    private List<Api> apis;
}
