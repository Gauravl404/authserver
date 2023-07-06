package com.cobodeal.authserver.dao.role;

import com.cobodeal.authserver.dto.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleDaoImpl implements IRoleDao {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Role> getAllRoles() {
        try {
            String sql = "SELECT role_id,role_name FROM tbl_role;";
            return jdbcTemplate.query(sql, (rs, i) -> Role.builder()
                    .roleId(rs.getInt("role_id"))
                    .role(rs.getString("role_name"))
                    .build());
        } catch (Exception e) {
            log.error("Failed to fetch all roles because {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Role> getRole(Integer id) {
        try {
            String sql = "SELECT role_id,role_name FROM tbl_role WHERE role_id = ? ;";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, i) -> Role.builder()
                    .roleId(rs.getInt("role_id"))
                    .role(rs.getString("role_name"))
                    .build(),id));
        } catch (Exception e) {
            log.error("Failed to fetch  role because {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Role> createRole(Role role) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "INSERT INTO tbl_role (role_name) VALUES (?);";
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, role.getRole());
                return ps;
            }, keyHolder);

            Integer roleId = keyHolder.getKey().intValue();

            return Optional.of(Role.builder()
                    .roleId(roleId)
                    .role(role.getRole())
                    .build());
        } catch (Exception e) {
            log.error("Failed to create role because {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Role> updateRole(Role role) {
        try {
            String sql = "UPDATE tbl_role SET role_name = ? WHERE role_id = ? ";
            return jdbcTemplate.update(sql,ps -> {
                ps.setString(1,role.getRole());
                ps.setInt(2,role.getRoleId());
            }) > 0 ? Optional.of(role):Optional.empty();
        } catch (Exception e) {
            log.error("Failed to update role because {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteRole(Integer roleId) {
        String sql = "DELETE FROM tbl_role  WHERE role_id = ? ";
        return jdbcTemplate.update(sql,ps -> ps.setInt(1,roleId))>0;
    }
}
