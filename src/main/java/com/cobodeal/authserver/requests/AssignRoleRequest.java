package com.cobodeal.authserver.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleRequest {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("role_id")
    private Integer roleId;
}
