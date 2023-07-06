package com.cobodeal.authserver.service;

import com.cobodeal.authserver.dto.User;

public interface IUserDetailsService {
    public User loadUserByUserName(String email);
}
