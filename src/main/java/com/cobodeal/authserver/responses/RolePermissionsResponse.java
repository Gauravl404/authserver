package com.cobodeal.authserver.responses;

import com.cobodeal.authserver.dto.Api;
import com.cobodeal.authserver.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionsResponse {
    private Role role;
    private List<Api> apis;
}
