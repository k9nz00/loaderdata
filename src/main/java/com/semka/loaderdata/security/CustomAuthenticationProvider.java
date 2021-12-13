package com.semka.loaderdata.security;

import com.semka.loaderdata.dao.UserDao;
import com.semka.loaderdata.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDao userDao;

    @Autowired
    public CustomAuthenticationProvider(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        Collection<String> roles = getUserRoles(username, password)
                .orElseThrow(() -> new BadCredentialsException("bad credentials"));
        UserDetails userDetails = User.builder()
                .username(username)
                .password(password)
                .roles(roles.stream()
                        .map(String::toUpperCase)
                        .toArray(String[]::new))
                .build();
        return new UsernamePasswordAuthenticationToken(userDetails.getPassword(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Optional<Collection<String>> getUserRoles(String username, String password) {
        UserEntity userEntity = userDao.getUserByLoginAndPassword(username, password);
        return Optional.ofNullable(userEntity)
                .map(user -> Arrays.asList(userDao.getRole(user.getRoleId()).getAuthorities()));
    }
}
