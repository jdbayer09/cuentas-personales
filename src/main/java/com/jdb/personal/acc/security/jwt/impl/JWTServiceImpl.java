package com.jdb.personal.acc.security.jwt.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdb.personal.acc.security.SimpleGrantedAuthorityMixin;
import com.jdb.personal.acc.security.jwt.JWTService;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JWTServiceImpl implements JWTService {

    private static final Logger logger = LoggerFactory.getLogger(JWTServiceImpl.class);
    private static final Long EXPIRATION_TOKEN = 36000000L;
    private static final SecretKey SECRET_KEY = new SecretKeySpec("JUL1AN B4Y3R P3R50N4L 4CC0UNT5 50FTW4ARE 53RV1C3S".getBytes(), SignatureAlgorithm.HS384.getJcaName());

    @Override
    public String create(Authentication auth) throws IOException {
        String username = ((User) auth.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
        Claims claims = Jwts.claims();

        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TOKEN);

        logger.info("La sesion de: '" + username + "', expira: '" + expirationDate + "'");
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setIssuer("L.O. Trading Corp.")
                .setId(new Date().getTime() + "")
                .setExpiration(expirationDate)
                .compact();
    }

    @Override
    public boolean validate(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(resolve(token)).getBody();
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
        Object roles = getClaims(token).get("authorities");
        return Arrays.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
    }

    @Override
    public String resolve(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}
