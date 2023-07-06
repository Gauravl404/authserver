package com.cobodeal.authserver.responses.apiresponses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomFailureEnum {
    REGISTRATION_FAILED(false,101,"User registration failed."),
    LOGIN_FAILED(false,102,"Login failed"),
    VERIFICATION_FAILED(false,103,"Verification failed"),
    GET_ALL_API_FAILED(false, 104,"All Api fetched success fully"),
    GET_API_FAILED(false, 105,"Api fetched success fully"),
    CREATE_API_FAILED(false, 106,"Api created successfully."),
    UPDATE_API_FAILED(false, 107,"Api updated successfully"),
    DELETE_API_FAILED(false,108,"Api deleted successfully"),
    GET_ALL_ROLE_FAILED(false,109,"All Role fetched success fully"),
    GET_ROLE_FAILED(false,110,"Role fetched success fully"),
    CREATE_ROLE_FAILED(false,111,"Role created successfully."),
    UPDATE_ROLE_FAILED(false,112,"Role updated successfully"),
    DELETE_ROLE_FAILED(false,113,"Role deleted successfully");


    private final boolean status;
    private final Integer code;
    private final String message;
}
