package com.mz.auth.service;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.mz.auth.repository.UserRepository;
import com.mz.auth.entity.CustomUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.mz.auth.entity.enums.UserRole;
import com.mz.auth.dto.RegisterDTO;
import com.mz.auth.security.exception.UserAlreadyExistsException;


@Service
public class CustomUserDetailsService implements UserDetailsService{

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

public CustomUserDetailsService(UserRepository userRepository,PasswordEncoder passwordEncoder){
this.userRepository=userRepository;
this.passwordEncoder=passwordEncoder;
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
}



public void registerUser(RegisterDTO data){
if(userRepository.existsByUsername(data.username())){
throw new UserAlreadyExistsException("Registration failed");
}

String encriptedPassword = passwordEncoder.encode(data.password());
CustomUser newUser = new CustomUser(data.username(),encriptedPassword,UserRole.USER);
 userRepository.save(newUser);
}





}
