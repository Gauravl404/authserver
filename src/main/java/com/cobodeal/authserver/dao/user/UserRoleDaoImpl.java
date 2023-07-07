package com.cobodeal.authserver.dao.user;

import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.requests.AssignRoleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRoleDaoImpl implements IUserRoleDao{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Boolean assignRoleToUser(AssignRoleRequest request) {
        try{
            String sql = "UPDATE tbl_user SET role_id = ? WHERE user_id = ?";
            return jdbcTemplate.update(sql,ps -> {
                ps.setInt(1,request.getRoleId());
                ps.setInt(2,request.getUserId());
            })>0;

        }catch (Exception e){
            log.info("Failed to assign {} to the {}} ",request.getRoleId(),request.getUserId());
            return false;
        }
    }

    @Override
    public Optional<Role> getAssignedRoleToUser(Integer userId) {
        try {
            String sql = "SELECT r.role_id,r.role_name FROM  tbl_user u LEFT JOIN tbl_role r ON u.role_id = r.role_id WHERE u.user_id = ? ;";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, i) -> Role.builder()
                    .roleId(rs.getInt("role_id"))
                    .role(rs.getString("role_name"))
                    .build(),userId));
        } catch (Exception e) {
            log.error("Failed to fetch  role of user {} because {}",userId, e.getMessage());
            return Optional.empty();
        }
    }
}
