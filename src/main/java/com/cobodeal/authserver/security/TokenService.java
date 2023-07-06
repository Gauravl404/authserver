package com.cobodeal.authserver.security;

import com.cobodeal.authserver.dao.token.TokenDao;
import com.cobodeal.authserver.dto.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenDao tokenDao;

    public List<Token> findAllValidTokenByUser(Integer id){
       return tokenDao.findAllValidTokenByUser(id);
    }

    public Optional<Token> findByToken(String token){
        return tokenDao.findByToken(token);

    }

    public void save(Token token){
        tokenDao.save(token);
    }

    public void saveAll(List<Token> validUserTokens) {
        tokenDao.saveAll(validUserTokens);
    }
}
