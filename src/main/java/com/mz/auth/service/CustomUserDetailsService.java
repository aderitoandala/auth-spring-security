package com.mz.auth.service;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



@Service
public class CustomUserDetailsService implements UserDetailsService{

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
return User.builder().username(username).password("$2a$12$5yuF5LqAHM7H13sBsrdF8.VBBs56LF8lW62sMgSBoSCc91He7CI3e").build();
}

}
