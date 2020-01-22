package com.social.network.service;

import com.social.network.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
public interface UserService {
    @PostMapping(path = "/user/login")
    String login(@RequestBody final User user);
    @PostMapping(path = "/user/register")
    boolean register(@RequestBody final User user);
    @PostMapping(path = "/api/test")
    String test();
}
