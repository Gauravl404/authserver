package com.cobodeal.authserver.dao.permission;

import com.cobodeal.authserver.dto.Api;

import java.util.List;
import java.util.Optional;

public interface IApiDao {
    List<Api> getAllApis();
    List<Api>  getApis(List<Integer> ids);
    Optional<Api> getApi(Integer id);
    Optional<Api> createApi(Api api);

    Optional<Api> updateApi(Api api);

    Boolean deleteAPI(Integer apiId);
}
