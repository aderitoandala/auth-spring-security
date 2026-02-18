package com.mz.auth.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



@Configuration
public class SecurityConfig{

@Bean
public UserDetailsService userDetailsService(){
UserDetails user = User.builder().username("aderito123").password("{noop}1234").build();
return new InMemoryUserDetailsManager(user);
}

}
