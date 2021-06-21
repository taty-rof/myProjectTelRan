package com.telran.auth.service;


import com.telran.auth.dao.*;
import com.telran.auth.dao.entity.UserCredentialsEntity;
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

    UserCredentialsRepo userCredentialsRepository;

    @Autowired
    public UserDetailsServiceImpl(@Lazy UserCredentialsRepo repo) {
        this.userCredentialsRepository =repo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        UserCredentialsEntity entity = userCredentialsRepository.getUser(username);
        System.out.println("From USERDETAILSERVICE "+entity);
        if (entity == null){
            throw new UsernameNotFoundException(username);
        }

        return new User(entity.getUsername(),
                entity.getPassword(),
                AuthorityUtils.createAuthorityList(entity.getRoles()));
    }
}
