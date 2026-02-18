package com.mz.auth.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig{

@Bean
public UserDetailsService userDetailsService(){
UserDetails user = User.builder().username("aderito123").password("$2a$12$5yuF5LqAHM7H13sBsrdF8.VBBs56LF8lW62sMgSBoSCc91He7CI3e").build();
return new InMemoryUserDetailsManager(user);
}

@Bean
public PasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
}

}
