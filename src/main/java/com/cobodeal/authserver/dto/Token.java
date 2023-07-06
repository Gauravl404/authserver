package com.cobodeal.authserver.dto;

import com.cobodeal.authserver.constants.TokenType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Token {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("token")
    private String token;
    @JsonProperty("token_type")
    private TokenType tokenType = TokenType.BEARER;
    @JsonProperty("revoked")
    private Boolean revoked;
    @JsonProperty("expired")
    private Boolean expired;
    @JsonProperty("user_id")
    private Integer userId;
}
