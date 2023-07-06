package com.cobodeal.authserver.responses.apiresponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response<T> {
    private Boolean status;
    private String message;
    private Integer message_code;
    private T data;

    public Response(CustomSuccessEnum success, T data) {
        this.status = success.isStatus();
        this.message = success.getMessage();
        this.message_code = success.getCode();
        this.data = data;
    }

    public Response(CustomFailureEnum failure, T data) {
        this.status = failure.isStatus();
        this.message = failure.getMessage();
        this.message_code = failure.getCode();
        this.data = data;
    }
}
