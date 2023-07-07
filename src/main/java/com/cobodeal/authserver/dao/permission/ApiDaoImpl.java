package com.cobodeal.authserver.dao.permission;

import com.cobodeal.authserver.dto.Api;
import com.cobodeal.authserver.dto.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiDaoImpl implements IApiDao{

    private final JdbcTemplate jdbcTemplate;
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
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "INSERT INTO tbl_api (end_point, method, version, status) VALUES (?,?,?,?);";
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, api.getEndPoint());
                ps.setString(2, api.getMethod().name());
                ps.setInt(3, api.getVersion());
                ps.setString(4, api.getStatus());
                return ps;
            }, keyHolder);

            Integer apiId = keyHolder.getKey().intValue();

            return Optional.of(Api.builder()
                    .apiId(apiId)
                    .endPoint(api.getEndPoint())
                    .method(api.getMethod())
                    .version(api.getVersion())
                    .status(api.getStatus())
                    .build());

        } catch (Exception e) {
            log.error("Failed to create role because {}", e.getMessage());
            return Optional.empty();
        }
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
