package com.cobodeal.authserver.service;

import com.cobodeal.authserver.dto.Api;
import com.cobodeal.authserver.responses.apiresponses.Response;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IApiService {
    ResponseEntity<Response<List<Api>>> getAllApis();

    ResponseEntity<Response<Api>> getApi(Integer apiId);

    ResponseEntity<Response<Api>> createApi(Api api);

    ResponseEntity<Response<Api>> updateApi(Api api);

    ResponseEntity<Response<Boolean>> deleteApi(Integer apiId);
}
