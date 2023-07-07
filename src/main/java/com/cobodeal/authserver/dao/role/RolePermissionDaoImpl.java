package com.cobodeal.authserver.dao.role;

import com.cobodeal.authserver.requests.RolePermissionRequest;
import com.cobodeal.authserver.responses.RolePermissionsResponse;
import com.cobodeal.authserver.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
        //todo
        return Optional.empty();
    }
}
