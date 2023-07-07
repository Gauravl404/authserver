package com.cobodeal.authserver.responses.apiresponses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomFailureEnum {
    REGISTRATION_FAILED(false,101,"User registration failed."),
    LOGIN_FAILED(false,102,"Login failed"),
    VERIFICATION_FAILED(false,103,"Verification failed"),
    GET_ALL_API_FAILED(false, 104,"All Api fetched failed"),
    GET_API_FAILED(false, 105,"Api fetched failed"),
    CREATE_API_FAILED(false, 106,"Api created failed."),
    UPDATE_API_FAILED(false, 107,"Api updated failed"),
    DELETE_API_FAILED(false,108,"Api deleted failed"),
    GET_ALL_ROLE_FAILED(false,109,"All Role fetched failed"),
    GET_ROLE_FAILED(false,110,"Role fetched failed"),
    CREATE_ROLE_FAILED(false,111,"Role created failed."),
    UPDATE_ROLE_FAILED(false,112,"Role updated failed"),
    DELETE_ROLE_FAILED(false,113,"Role deleted failed"),
    ASSIGN_ROLE_FAILED(false,114,"Role assigned failed"),
    GET_ASSIGNED_ROLE_FAILED(false,115,"Assigned role fetch failed"),
    ADD_ROLE_PERMISSION_FAILED(false,116,"API permissions added to role failed"),
    UPDATE_ROLE_PERMISSION_FAILED(false,117,"API permissions updated to role failed"),
    DELETE_ROLE_PERMISSION_FAILED(false,118,"Role permission deleted  failed"),
    GET_ROLE_PERMISSION_FAILED(false,119,"Role permission fetch failed"),
    API_AUTHORIZE_FAILED(true,120,"API is not authorized for user")

    ;


    private final boolean status;
    private final Integer code;
    private final String message;
}
