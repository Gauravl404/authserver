package com.cobodeal.authserver.dao.role;


import com.cobodeal.authserver.constants.MethodType;
import com.cobodeal.authserver.dto.Api;
import com.cobodeal.authserver.requests.RolePermissionRequest;
import com.cobodeal.authserver.responses.RolePermissionsResponse;
import com.cobodeal.authserver.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolePermissionDaoImpl implements IRolePermissionDao{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Boolean addRolePermissions(RolePermissionRequest request) {
        try {
            String sql = " INSERT INTO tbl_role_permission ( role_id,api_id) VALUES (?,?) ";
            List<Object[]> batchArgs = request.getApiIds().stream().map(api ->
                            new Object[]{request.getRoleId(),api})
                    .toList();
            int[] updatedRows = jdbcTemplate.batchUpdate(sql, batchArgs);
            log.info("Token saved for {} many rows ", updatedRows.length);
            return !Arrays.stream(updatedRows).boxed().reduce(Integer::sum).orElse(0).equals(0);
        } catch (Exception e) {
            log.error("Failed to save role permissions because {}", e.getMessage());
            return false;
        }
    }


    @Override
    public Boolean deleteRolePermissions(Integer roleId) {
        try {
            String sql = " DELETE FROM tbl_role_permission WHERE role_id = ? ";
            return jdbcTemplate.update(sql,roleId)>0;
        } catch (Exception e) {
            log.error("Failed to delete role permissions because {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteRolesMultiplePermissions(Integer roleId, List<Integer> apiIds) {
        try {
            String sql = " DELETE FROM tbl_role_permission WHERE role_id = ? AND api_id in "+ StringUtil.convertIntegerToString(apiIds);
            return jdbcTemplate.update(sql,roleId)>0;
        } catch (Exception e) {
            log.error("Failed to delete role permissions because {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<RolePermissionsResponse> getRolePermissions(Integer roleId) {
        try {
            String sql = "SELECT a.api_id, a.end_point, a.method, a.version, a.status from tbl_role r LEFT JOIN tbl_role_permission p ON r.role_id = p.role_id\n" +
                    "LEFT JOIN tbl_api a ON a.api_id = p.api_id where r.role_id = ? ;";
            var apis =  Optional.of(
                    jdbcTemplate.query(sql,(rs,i)->{
                        return Api.builder()
                                .apiId(rs.getInt("api_id"))
                                .endPoint(rs.getString("end_point"))
                                .method(MethodType.valueOf(rs.getString("method")))
                                .version(rs.getInt("version"))
                                .status(rs.getString("status"))
                                .build();
                    },roleId)
            );

            return Optional.of(RolePermissionsResponse.builder()
                    .apis(apis.orElse(new ArrayList<>()))
                    .build());

        } catch (Exception e) {
            log.error("Failed to get role permissions because {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Boolean checkAuthorizationForApi(String email, String method, String endPoint) {
        try{
            String sql = "SELECT \n" +
                    "    COUNT(a.api_id) > 0 AS is_authorize\n" +
                    "FROM\n" +
                    "    tbl_user u\n" +
                    "        LEFT JOIN\n" +
                    "    tbl_role_permission p ON u.role_id = p.role_id\n" +
                    "        LEFT JOIN\n" +
                    "    tbl_api a ON p.api_id = a.api_id\n" +
                    "WHERE\n" +
                    "    u.email = ?\n" +
                    "        AND a.method = ?\n"+
                    "        AND a.end_point = ? ";

            return jdbcTemplate.queryForObject(sql, Boolean.class);
        }catch (Exception e){
            log.info("Error while validating api {} {} for user {} error={}",method,endPoint,email,e.getMessage());
            return false;
        }
    }
}
