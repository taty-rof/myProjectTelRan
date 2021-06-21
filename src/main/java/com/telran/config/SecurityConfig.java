package com.telran.config;

import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.config.filter.AuthenticationFilter;
import com.telran.config.filter.AuthorizationFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    UserDetailsService userDetailsService;
    UserCredentialsRepo repo;
    Environment env;

    @Autowired
    public SecurityConfig( UserDetailsService userDetailsService,
            @Lazy UserCredentialsRepo repo,
            Environment env){
        this.userDetailsService = userDetailsService;
        this.repo =  repo;
        this.env =  env;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()

                .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager(),repo))
//                .and()
                .authorizeRequests()
                .mvcMatchers( "/user/registration").permitAll()
                .mvcMatchers( "/user/{userEmail}/password/reset").permitAll()
                .mvcMatchers( HttpMethod.DELETE,"/user/{userEmail}").hasRole("ADMIN")
                .mvcMatchers( "/user/{userEmail}").hasRole("STUDENT")
                .mvcMatchers(HttpMethod.POST,"/user/addUser").hasRole("ADMIN")
                .anyRequest().permitAll();

    }


    @SneakyThrows
    private AuthenticationFilter getAuthenticationFilter(){
        AuthenticationFilter authenticationFilterFilter =
                new AuthenticationFilter(authenticationManager());
        authenticationFilterFilter.setFilterProcessesUrl("/user/login/");
        return authenticationFilterFilter;
    }


}
