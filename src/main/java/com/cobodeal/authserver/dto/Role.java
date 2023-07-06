package com.cobodeal.authserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Role {

    @JsonProperty("role_id")
    private Integer roleId;
    @JsonProperty("role_name")
    private String role;


    public List<SimpleGrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority("ROLE_" + this.getRole().toUpperCase()));
    }



}
