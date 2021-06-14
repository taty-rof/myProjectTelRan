package com.telran.auth.service;


import com.telran.auth.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepo userRepository;

    @Autowired
    public UserDetailsServiceImpl(@Lazy UserRepo repo) {
        this.userRepository=repo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity entity = userRepository.getUser(username);

        if (entity == null){
            throw new UsernameNotFoundException(username);
        }

        return new User(entity.getUsername(),
                entity.getPassword(),
                entity.getEnabled(),
                false,
                false,
                false,
                AuthorityUtils.createAuthorityList(entity.getRoles()));
    }
}
