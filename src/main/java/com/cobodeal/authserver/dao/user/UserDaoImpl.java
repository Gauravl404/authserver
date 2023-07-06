package com.cobodeal.authserver.dao.user;

import com.cobodeal.authserver.dto.Role;
import com.cobodeal.authserver.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDaoImpl implements IUserDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT user_id, first_name, email, password,last_name, email_verified,r.role_name,u.role_id  FROM tbl_user u LEFT JOIN tbl_role r ON r.role_id = u.role_id WHERE email = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    (rs, i) -> (User.builder()
                            .id(rs.getInt("user_id"))
                            .firstName(rs.getString("first_name"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .lastName(rs.getString("last_name"))
                            .verified(rs.getBoolean("email_verified"))
                            .role(Role.builder()
                                    .roleId(rs.getInt("role_id"))
                                    .role(rs.getString("role_name"))
                                    .build())
                            .build())
                    , email));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void saveUser(User user) {

        try {
            String sql = "INSERT INTO tbl_user (first_name, email, password, last_name, email_verified, role_id) VALUES(?,?,?,?,?,?);";

            jdbcTemplate.update(sql, ps -> {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getLastName());
                ps.setBoolean(5, user.getVerified());
                ps.setInt(6, user.getRole().getRoleId());
            });

        } catch (Exception e) {
            log.info("Error while saving player = {}", e.getMessage());
        }

    }

    @Override
    public Boolean saveVerificationToken(User user, String token) {
        try {
            String sql = "INSERT INTO tbl_email_verification(email,verification_code) VALUES(?,?);";

            return jdbcTemplate.update(sql, ps -> {
                ps.setString(1, user.getEmail());
                ps.setString(2, token);
            }) != 0;

        } catch (Exception e) {
            log.info("Error while saving player = {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<User> findByVerificationToken(String token) {
        String sql = "SELECT user_id,first_name, u.email, password, last_name, email_verified, r.role_name,u.role_id  FROM tbl_user u  LEFT JOIN tbl_email_verification e  ON e.email = u.email LEFT JOIN tbl_role r ON r.role_id = u.user_role WHERE e.verification_code = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    (rs, i) -> (
                            User.builder()
                                    .id(rs.getInt("user_id"))
                                    .firstName(rs.getString("first_name"))
                                    .email(rs.getString("email"))
                                    .password(rs.getString("password"))
                                    .lastName(rs.getString("last_name"))
                                    .verified(rs.getBoolean("email_verified"))
                                    .role(Role.builder()
                                            .roleId(rs.getInt("role_id"))
                                            .role(rs.getString("role_name"))
                                            .build())
                                    .build())
                    , token));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean verifyUser(Integer id) {

        try {
            String sql = "UPDATE tbl_user SET email_verified = 1 where user_id = ?";
            return jdbcTemplate.update(sql, ps -> {
                ps.setInt(1, id);
            }) != 0;

        } catch (Exception e) {
            log.info("Error while verifying player = {}", e.getMessage());
            return false;
        }
    }
}
