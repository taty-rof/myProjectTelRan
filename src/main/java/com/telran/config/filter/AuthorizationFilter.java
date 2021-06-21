package com.telran.config.filter;

import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.entity.UserCredentialsEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class AuthorizationFilter extends BasicAuthenticationFilter {
//    Environment env;
    @Autowired
    UserCredentialsRepo repository;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
//                               Environment env,
                               UserCredentialsRepo repository) {
        super(authenticationManager);
//        this.env = env;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
//        String authorizationHeader = request.getHeader(env.getProperty("authorization.token.header.name"));
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(authorizationHeader);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String authorizationHeader){
        String token = authorizationHeader.replace("Bearer","");

        String secretKey = "21ee2ca80dc1c6262b8b8cf92ca38e2a98c02790f88c6d16d0c051a7fa935863";

        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String email = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        if (email == null){
            return null;
        }
        UserCredentialsEntity entity = repository.getUser(email);
        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                AuthorityUtils.createAuthorityList(entity.getRoles())
        );
    }
}
