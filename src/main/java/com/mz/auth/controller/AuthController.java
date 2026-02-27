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

@RestController
@RequestMapping("/auth")
public class AuthController{

private final CustomUserDetailsService userService;

public AuthController(CustomUserDetailsService userService){
this.userService=userService;
}

@GetMapping
public ResponseEntity<String> startSecurity(){
return ResponseEntity.ok("Getting start with spring security");
}

@PostMapping("/signIn")
public ResponseEntity<Void>registerUser(@RequestBody RegisterDTO data){
userService.registerUser(data);
return ResponseEntity.status(HttpStatus.CREATED).build();
}



}
