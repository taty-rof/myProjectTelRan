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

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    UserCredentialsRepo userCredentialsRepository;

    @Autowired
    public UserDetailsServiceImpl(@Lazy UserCredentialsRepo repo) {
        this.userCredentialsRepository =repo;
    }


    @Override
    public UserDetails loadUserByUsername(String email) {
        UserCredentialsEntity entity = userCredentialsRepository.findById(email).get();
        System.out.println("From USERDETAILSERVICE "+entity);
        if (entity == null){
            throw new UsernameNotFoundException(email);
        }

        return new User(entity.getEmail(),
                entity.getPassword(),
                entity.getEnabled(),
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(entity.getRoles()));
    }
}
