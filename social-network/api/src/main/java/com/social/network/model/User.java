package com.social.network.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class User {
    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
