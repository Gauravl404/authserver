package com.cobodeal.authserver.responses.apiresponses;

import lombok.Getter;


@Getter
public enum CustomSuccessEnum {
    REGISTRATION_SUCCESS(true, 1,"User registration successFull, Check your email"),
    LOGIN_SUCCESS(true, 2,"Login successfully"),
    VERIFICATION_SUCCESS(true, 3,"Verified"),
    GET_ALL_API_SUCCESS(true, 4,"All Api fetched success fully"),
    GET_API_SUCCESS(true, 5,"Api fetched success fully"),
    CREATE_API_SUCCESS(true, 6,"Api created successfully."),
    UPDATE_API_SUCCESS(true, 7,"Api updated successfully"),
    DELETE_API_SUCCESS(true,8,"Api deleted successfully"),
    GET_ALL_ROLE_SUCCESS(true,9,"All Role fetched success fully"),
    GET_ROLE_SUCCESS(true,10,"Role fetched success fully"),
    CREATE_ROLE_SUCCESS(true,11,"Role created successfully."),
    UPDATE_ROLE_SUCCESS(true,12,"Role updated successfully"),
    DELETE_ROLE_SUCCESS(true,13,"Role deleted successfully"),
    ASSIGN_ROLE_SUCCESS(true,14,"Role assigned successfully"),
    GET_ASSIGNED_ROLE_SUCCESS(true,15,"Assigned role fetch successfully"),
    ADD_ROLE_PERMISSION_SUCCESS(true,16,"API permissions added to role successfully"),
    UPDATE_ROLE_PERMISSION_SUCCESS(true,17,"API permissions updated to role successfully"),
    DELETE_ROLE_PERMISSION_SUCCESS(true,18,"Role permission deleted  successfully"),
    GET_ROLE_PERMISSION_SUCCESS(true,19,"Role permission fetch successfully")
    ;

    CustomSuccessEnum(boolean status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    private final boolean status;
    private final Integer code;
    private final String message;
}
