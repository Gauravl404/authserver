package com.cobodeal.authserver.service.impl;

import com.cobodeal.authserver.dao.role.IRoleDao;
import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.responses.apiresponses.CustomFailureEnum;
import com.cobodeal.authserver.responses.apiresponses.CustomSuccessEnum;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleDao roleDao;


    @Override
    public ResponseEntity<Response<List<Role>>> getAllRoles() {
        var list = roleDao.getAllRoles();
        return list.isEmpty() ?
                new ResponseEntity<Response<List<Role>>>(
                new Response<List<Role>>(
                        CustomFailureEnum.GET_ALL_ROLE_FAILED,list)
                , HttpStatus.OK)
                : new ResponseEntity<Response<List<Role>>>(
                new Response<List<Role>>(
                        CustomSuccessEnum.GET_ALL_ROLE_SUCCESS,list)
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<Role>> getRole(Integer roleId) {
        var role = roleDao.getRole(roleId);

        return role.map(value -> new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.GET_ROLE_SUCCESS, value)
                , HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.GET_ROLE_FAILED, Role.builder().build())
                , HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Response<Role>> createRole(String name) {
        var res = roleDao.createRole(Role.builder().role(name).build());

        return res.map(value -> new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.CREATE_ROLE_SUCCESS, value)
                , HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.CREATE_ROLE_FAILED, Role.builder().build())
                , HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Response<Role>> updateRole(Integer roleId, String role) {
        var res = roleDao.updateRole(Role.builder().roleId(roleId).role(role).build());

        return res.map(value -> new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.UPDATE_ROLE_SUCCESS, value)
                , HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.UPDATE_ROLE_FAILED, Role.builder().build())
                , HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Response<Boolean>> deleteRole(Integer roleId) {
        var res =  roleDao.deleteRole(roleId);
        return res ?  new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.DELETE_ROLE_SUCCESS, true)
                , HttpStatus.OK)
                :
                new ResponseEntity<>(
                        new Response<>(
                                CustomFailureEnum.DELETE_ROLE_FAILED, false)
                        , HttpStatus.OK);
    }
}
