package com.social.network.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Role implements GrantedAuthority {
    @EqualsAndHashCode.Include
    private Long id;
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
