package com.cobodeal.authserver.dao.permission;

import com.cobodeal.authserver.dto.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiDaoImpl implements IApiDao{
    @Override
    public List<Api> getAllApis() {
        return null;
    }

    @Override
    public Optional<Api> getApi(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Api> createApi(Api api) {
        return Optional.empty();
    }

    @Override
    public Optional<Api> updateApi(Api api) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteAPI(Integer apiId) {
        return null;
    }
}
