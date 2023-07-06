package com.cobodeal.authserver.dao.token;

import com.cobodeal.authserver.constants.TokenType;
import com.cobodeal.authserver.dto.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Token> findAllValidTokenByUser(Integer id) {
        try {
            String sql = "SELECT id, token, token_type, expired, revoked, user_id FROM tbl_token WHERE user_id = ?;";
            return jdbcTemplate.query(sql, (rs, i) -> Token.builder()
                    .id(rs.getInt("id"))
                    .token(rs.getString("token"))
                    .tokenType(TokenType.valueOf(rs.getString("token_type")))
                    .expired(rs.getBoolean("expired"))
                    .revoked(rs.getBoolean("revoked"))
                    .userId(rs.getInt("user_id"))
                    .build(), id);
        } catch (Exception e) {
            log.error("Failed to fetch all tokens by user because {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public Optional<Token> findByToken(String token) {
        try {
            String sql = "SELECT id, token, token_type, expired, revoked, user_id FROM tbl_token WHERE token = ?;";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, i) -> Token.builder()
                    .id(rs.getInt("id"))
                    .token(rs.getString("token"))
                    .tokenType(TokenType.valueOf(rs.getString("token_type")))
                    .expired(rs.getBoolean("expired"))
                    .revoked(rs.getBoolean("revoked"))
                    .userId(rs.getInt("user_id"))
                    .build(), token));
        } catch (Exception e) {
            log.error("Failed to fetch token  because {}", e.getMessage());
            return Optional.empty();
        }
    }

    public void save(Token token) {
        try {
            String sql = " INSERT INTO tbl_token ( token, token_type, expired, revoked, user_id) VALUES (?,?,?,?,?) " +
                    " ON DUPLICATE KEY UPDATE token_type = ?, expired = ?,revoked = ?, user_id = ? ;";
            jdbcTemplate.update(sql, ps -> {
                ps.setString(1, token.getToken());
                ps.setString(2, token.getTokenType().name());
                ps.setBoolean(3, token.getExpired());
                ps.setBoolean(4, token.getRevoked());
                ps.setInt(5, token.getUserId());
                ps.setString(6, token.getTokenType().name());
                ps.setBoolean(7, token.getExpired());
                ps.setBoolean(8, token.getRevoked());
                ps.setInt(9, token.getUserId());
            });

            log.info("Token saved for user {}", token.getUserId());
        } catch (Exception e) {
            log.error("Failed to save token because {}", e.getMessage());
        }
    }

    public void saveAll(List<Token> validUserTokens) {
        try {
            String sql = " INSERT INTO tbl_token ( token, token_type, expired, revoked, user_id) VALUES (?,?,?,?,?) " +
                    " ON DUPLICATE KEY UPDATE token_type = ?, expired = ?,revoked = ?, user_id = ? ;";

            List<Object[]> batchArgs = validUserTokens.stream().map(token ->
                            new Object[]{token.getToken(),
                                    token.getTokenType().name(),
                                    token.getExpired(),
                                    token.getRevoked(),
                                    token.getUserId(),
                                    token.getTokenType().name(),
                                    token.getExpired(),
                                    token.getRevoked(),
                                    token.getUserId()})
                    .toList();

            int[] updatedRows = jdbcTemplate.batchUpdate(sql, batchArgs);

            log.info("Token saved for {} many rows ", updatedRows.length);
        } catch (Exception e) {
            log.error("Failed to save token because {}", e.getMessage());
        }
    }
}
