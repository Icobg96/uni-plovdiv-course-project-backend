package com.social.network.service;

import com.social.network.model.AuthToken;
import com.social.network.model.AuthTokenEntity;
import com.social.network.model.RoleEntity;
import com.social.network.model.UserEntity;
import com.social.network.repository.AuthTokenRepository;
import com.social.network.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Created on 4.12.2019 г.
 * @Author Hristo Ispirov
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    private final UserRepository userRepository;
    private final AuthTokenRepository authTokenRepository;
    public AuthenticateServiceImpl(final UserRepository userRepository, final AuthTokenRepository authTokenRepository){
        this.userRepository = userRepository;
        this.authTokenRepository = authTokenRepository;
    }
    @Override
    @Transactional
    public AuthToken login(final String username, final String password) {
        final Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            final String token = UUID.randomUUID().toString();
            final AuthTokenEntity authToken = authTokenRepository.save(new AuthTokenEntity(token));
            optionalUser.get().setAuthToken(authToken);
            final UserEntity savedUser = userRepository.save(optionalUser.get());
            return AuthTokenEntity.toDto(savedUser.getAuthToken());
        }
        return null;
    }

    @Override
    @Transactional
    public Optional<User> findByToken(final String token) {
        if(StringUtils.isEmpty(token)){
            return Optional.empty();
        }
        final String clearToken = token.replace("Bearer","").trim();
        final Optional<AuthTokenEntity> optionalAuthToken = authTokenRepository.findByToken(clearToken);
        if(optionalAuthToken.isPresent()){
            final Optional<UserEntity> optionalUser = userRepository.findByAuthTokenId(optionalAuthToken.get().getId());
            if(optionalUser.isPresent()) {
                final User user = new User(optionalUser.get().getUsername(), optionalUser.get().getPassword(), true, true, true, true,
                        mapRolToGrantedAuthority(optionalUser.get().getRoles()));
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    private Set<GrantedAuthority> mapRolToGrantedAuthority(final Set<RoleEntity> roles){
        if(CollectionUtils.isEmpty(roles)){
            return Collections.emptySet();
        }
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());
    }
}
