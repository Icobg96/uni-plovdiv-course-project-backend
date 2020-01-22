package com.social.network.service;

import com.social.network.model.AuthToken;
import org.springframework.security.core.userdetails.User;


import java.util.Optional;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
public interface AuthenticateService {
    AuthToken login(final String username, final String password);
    Optional<User> findByToken(final String token);
}
