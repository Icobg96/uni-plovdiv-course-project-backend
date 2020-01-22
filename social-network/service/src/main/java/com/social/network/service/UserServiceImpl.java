package com.social.network.service;

import com.social.network.model.AuthToken;
import com.social.network.model.User;
import com.social.network.model.UserEntity;
import com.social.network.repository.UserRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
@RestController
public class UserServiceImpl implements UserService {
    private AuthenticateService authenticateService;
    private UserRepository userRepository;
    public UserServiceImpl(final AuthenticateService authenticateService, final UserRepository userRepository){
        this.authenticateService = authenticateService;
        this.userRepository = userRepository;
    }
    @Override
    public String login(final User user) {
        if(user == null || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return "";
        }
        final AuthToken authToken = authenticateService.login(user.getUsername(), user.getPassword());
        if(authToken == null || StringUtils.isEmpty(authToken.getToken())){
            return "";
        }
        return authToken.getToken();
    }

    @Override
    public boolean register(final User user) {
        if(user == null || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return false;
        }
        userRepository.save(UserEntity.toEntity(user));
        return true;
    }

    @Override
    public String test() {
        return "Test success!";
    }
}
