package com.cobodeal.authserver.dao.user;

import com.cobodeal.authserver.dto.User;

import java.util.Optional;

public interface IUserDao {

    Optional<User> findByEmail(String email);

    void saveUser(User user);

    Boolean saveVerificationToken(User user,String token);

    Optional<User> findByVerificationToken(String token);

    Boolean verifyUser(Integer id);
}
