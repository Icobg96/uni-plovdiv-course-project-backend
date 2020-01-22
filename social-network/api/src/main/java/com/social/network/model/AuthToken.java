package com.social.network.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AuthToken {
    @EqualsAndHashCode.Include
    private Long id;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
