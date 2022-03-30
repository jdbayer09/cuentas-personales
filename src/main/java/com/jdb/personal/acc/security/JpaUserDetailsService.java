package com.jdb.personal.acc.security;

import com.jdb.personal.acc.api.exception.NotUserException;
import com.jdb.personal.acc.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.jdb.personal.acc.api.entity.User user = null;
        try {
            user = userService.findByEmail(email);
        } catch (NotUserException ex) {
            throw new UsernameNotFoundException(email + " no existe");
        }

        if (user == null)
            throw new UsernameNotFoundException(email + " no existe");

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority( "ROLE_USER"));
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, roles);
    }
}