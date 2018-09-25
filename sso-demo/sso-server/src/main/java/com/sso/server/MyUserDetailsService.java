package com.sso.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Date:2018/9/25
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Component
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = "123456";
       return  new User(username,passwordEncoder.encode(password),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }
}
