package com.mz.auth.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
return httpSecurity
.csrf(csrf-> csrf.disable())
.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
.authorizeHttpRequests(auth->auth
.requestMatchers(HttpMethod.GET,"/auth").authenticated()
.requestMatchers(HttpMethod.POST,"/auth/signIn").permitAll()
)

.httpBasic(Customizer.withDefaults())

.build();

}

@Bean
public PasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
}


}
