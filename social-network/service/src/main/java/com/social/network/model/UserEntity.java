package com.social.network.model;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "SN_USER")
public class UserEntity {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false, length = 3000)
    private String password;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @CreationTimestamp
    private Date createAt;
    @UpdateTimestamp
    private Date updateAt;
    @OneToOne
    private AuthTokenEntity authToken;
    @OneToMany
    private Set<RoleEntity> roles;

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public AuthTokenEntity getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthTokenEntity authToken) {
        this.authToken = authToken;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
    public static UserEntity toEntity(final User user){
        if(user == null){
            return null;
        }
        final UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        if(!CollectionUtils.isEmpty(user.getRoles())){
            userEntity.setRoles(user.getRoles()
                    .stream()
                    .map(RoleEntity::toEntity)
                    .collect(Collectors.toSet()));
        }
        return userEntity;
    }
}
