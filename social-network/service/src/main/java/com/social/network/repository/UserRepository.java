package com.social.network.repository;

import com.social.network.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(final String username);
    Optional<UserEntity> findByAuthTokenId(final Long authTokenId);
}
