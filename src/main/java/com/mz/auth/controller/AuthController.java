package com.mz.auth.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.mz.auth.service.CustomUserDetailsService;
import com.mz.auth.entity.CustomUser;
import com.mz.auth.dto.RegisterDTO;
import com.mz.auth.dto.LoginRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.mz.auth.dto.LoginResponseDTO;
import com.mz.auth.security.TokenService;
import com.mz.auth.security.exception.TokenGenerationException;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/auth")
public class AuthController{

private final CustomUserDetailsService userService;

private final AuthenticationManager authenticationManager;

private final TokenService tokenService;



public AuthController(CustomUserDetailsService userService,AuthenticationManager authenticationManager,TokenService tokenService ){
this.userService=userService;
this.authenticationManager=authenticationManager;
this.tokenService=tokenService;
}

@GetMapping
public ResponseEntity<String> startSecurity(){
return ResponseEntity.ok("Getting start with spring security");
}

@PostMapping("/register")
public ResponseEntity<Void>registerUser(@RequestBody @Valid RegisterDTO data){
userService.registerUser(data);
return ResponseEntity.status(HttpStatus.CREATED).build();
}

@PostMapping("/login")
public ResponseEntity<LoginResponseDTO>login(@RequestBody @Valid LoginRequestDTO data){
var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.username(),data.password()));
if(!(auth.getPrincipal() instanceof CustomUser user)){
   throw new TokenGenerationException("Unexpected principal type");
}
String token = this.tokenService.generateToken(user);

return ResponseEntity.ok(new LoginResponseDTO(token));
}



}
