package com.cobodeal.authserver.service.impl;

import com.cobodeal.authserver.dao.permission.IApiDao;
import com.cobodeal.authserver.dao.role.IRoleDao;
import com.cobodeal.authserver.dao.role.IRolePermissionDao;
import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.requests.RolePermissionRequest;
import com.cobodeal.authserver.responses.RolePermissionsResponse;
import com.cobodeal.authserver.responses.apiresponses.CustomFailureEnum;
import com.cobodeal.authserver.responses.apiresponses.CustomSuccessEnum;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IRolesAndPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RoleAndPermissionServiceImpl implements IRolesAndPermissionService {

    private final IRolePermissionDao rolePermissionDao;

    private final IRoleDao roleDao;

    private final IApiDao apiDao;


    @Override
    public ResponseEntity<Response<String>> addRolePermissions(RolePermissionRequest request) {

        Boolean res = rolePermissionDao.addRolePermissions(request);

        return Boolean.TRUE.equals(res)
                ?
                new ResponseEntity<>(
                    new Response<>(
                            CustomSuccessEnum.ADD_ROLE_PERMISSION_SUCCESS, request.toString())
                    , HttpStatus.OK)
                :
                new ResponseEntity<>(
                    new Response<>(
                            CustomFailureEnum.ADD_ROLE_PERMISSION_FAILED, request.toString())
                    , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response<Boolean>> updateRolePermissions(RolePermissionRequest request) {

        Function<Integer,Boolean> deletePreviousPermissions = rolePermissionDao::deleteRolePermissions;

        var res = deletePreviousPermissions
                .andThen(response-> response && rolePermissionDao.addRolePermissions(request) )
                .apply(request.getRoleId());

        return Boolean.TRUE.equals(res)
                ?
                new ResponseEntity<>(
                        new Response<>(
                                CustomSuccessEnum.UPDATE_ROLE_PERMISSION_SUCCESS, true)
                        , HttpStatus.OK)
                :
                new ResponseEntity<>(
                        new Response<>(
                                CustomFailureEnum.UPDATE_ROLE_PERMISSION_FAILED, false)
                        , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response<String>> deleteRolePermissions(Integer roleId) {
        Boolean res = rolePermissionDao.deleteRolePermissions(roleId);

        return Boolean.TRUE.equals(res)
                ?
                new ResponseEntity<>(
                        new Response<>(
                                CustomSuccessEnum.DELETE_ROLE_PERMISSION_SUCCESS, roleId.toString())
                        , HttpStatus.OK)
                :
                new ResponseEntity<>(
                        new Response<>(
                                CustomFailureEnum.DELETE_ROLE_PERMISSION_FAILED, roleId.toString())
                        , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response<RolePermissionsResponse>> getRolePermissions(Integer roleId) {

        var res = rolePermissionDao.getRolePermissions(roleId);
        return res
                .map(value -> new ResponseEntity<>(
                        new Response<>(
                                CustomSuccessEnum.ADD_ROLE_PERMISSION_SUCCESS, value)
                        , HttpStatus.INTERNAL_SERVER_ERROR))
                .orElseGet(() -> new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.GET_ROLE_PERMISSION_FAILED, RolePermissionsResponse.builder().build())
                , HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    public ResponseEntity<Response<String>> deleteMultipleRolePermissions(RolePermissionRequest request) {
        Boolean res = rolePermissionDao.deleteRolesMultiplePermissions(request.getRoleId(),request.getApiIds());
        return Boolean.TRUE.equals(res)
                ?
                new ResponseEntity<>(
                        new Response<>(
                                CustomSuccessEnum.DELETE_ROLE_PERMISSION_SUCCESS, request.toString())
                        , HttpStatus.OK)
                :
                new ResponseEntity<>(
                        new Response<>(
                                CustomFailureEnum.DELETE_ROLE_PERMISSION_FAILED, request.toString())
                        , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
