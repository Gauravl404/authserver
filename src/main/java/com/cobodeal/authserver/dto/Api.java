package com.cobodeal.authserver.dto;

import com.cobodeal.authserver.constants.MethodType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Api {
    @JsonProperty("api_id")
    private Integer apiId;
    @JsonProperty("end_point")
    private String endPoint;
    @JsonProperty("method")
    private MethodType method;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("status")
    private String status;
}
