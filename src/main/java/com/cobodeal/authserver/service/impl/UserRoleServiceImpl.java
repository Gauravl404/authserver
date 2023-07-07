package com.cobodeal.authserver.service.impl;

import com.cobodeal.authserver.dao.user.IUserRoleDao;
import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.requests.AssignRoleRequest;
import com.cobodeal.authserver.responses.apiresponses.CustomFailureEnum;
import com.cobodeal.authserver.responses.apiresponses.CustomSuccessEnum;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements IUserRoleService {

    private final IUserRoleDao userRoleDao;

    @Override
    public ResponseEntity<Response<String>> assignRoleToUser(AssignRoleRequest request) {

        Boolean res = userRoleDao.assignRoleToUser(request);

        return res ?  new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.ASSIGN_ROLE_SUCCESS, request.toString())
                , HttpStatus.OK)
                :
                new ResponseEntity<>(
                        new Response<>(
                                CustomFailureEnum.ASSIGN_ROLE_FAILED, request.toString())
                        , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response<Role>> getRoleOfUser(Integer userId) {
        var res = userRoleDao.getAssignedRoleToUser(userId);

        return res.map(value -> new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.GET_ASSIGNED_ROLE_SUCCESS, value)
                , HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.GET_ASSIGNED_ROLE_FAILED, Role.builder().build())
                , HttpStatus.OK));
    }
}
