package com.cobodeal.authserver.service.impl;

import com.cobodeal.authserver.dao.permission.IApiDao;
import com.cobodeal.authserver.dto.Api;
import com.cobodeal.authserver.responses.apiresponses.CustomFailureEnum;
import com.cobodeal.authserver.responses.apiresponses.CustomSuccessEnum;
import com.cobodeal.authserver.responses.apiresponses.Response;
import com.cobodeal.authserver.service.IApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiServiceImpl implements IApiService {

    private final IApiDao apiDao;

    @Override
    public ResponseEntity<Response<List<Api>>> getAllApis() {

        var listOfApi = apiDao.getAllApis();
        return listOfApi.isEmpty() ?new ResponseEntity<Response<List<Api>>>(
                new Response<List<Api>>(
                        CustomFailureEnum.GET_ALL_API_FAILED,listOfApi)
                , HttpStatus.OK): new ResponseEntity<Response<List<Api>>>(
                new Response<List<Api>>(
                        CustomSuccessEnum.GET_ALL_API_SUCCESS,listOfApi)
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<Api>> getApi(Integer apiId) {
        var api = apiDao.getApi(apiId);

        return api.map(value -> new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.GET_API_SUCCESS, value)
                , HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.GET_API_FAILED, Api.builder().build())
                , HttpStatus.OK));

    }

    @Override
    public ResponseEntity<Response<Api>> createApi(Api api) {
        var res = apiDao.createApi(api);

        return res.map(value -> new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.CREATE_API_SUCCESS, value)
                , HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.CREATE_API_FAILED, Api.builder().build())
                , HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Response<Api>> updateApi(Api api) {

        var res = apiDao.updateApi(api);

        return res.map(value -> new ResponseEntity<>(
                new Response<>(
                        CustomSuccessEnum.UPDATE_API_SUCCESS, value)
                , HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.UPDATE_API_FAILED, Api.builder().build())
                , HttpStatus.OK));
    }

    @Override
    public ResponseEntity<Response<Boolean>> deleteApi(Integer apiId) {
       var res =  apiDao.deleteAPI(apiId);
       return res ?  new ResponseEntity<>(
               new Response<>(
                       CustomSuccessEnum.DELETE_API_SUCCESS, true)
               , HttpStatus.OK)
        :
        new ResponseEntity<>(
                new Response<>(
                        CustomFailureEnum.DELETE_API_FAILED, false)
                , HttpStatus.OK);

    }
}
