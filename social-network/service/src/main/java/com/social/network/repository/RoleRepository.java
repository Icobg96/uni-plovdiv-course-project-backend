package com.social.network.repository;

import com.social.network.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByAuthority(final String authority);
}
