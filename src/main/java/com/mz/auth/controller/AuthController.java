package com.mz.auth.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController{

@GetMapping
public ResponseEntity<String> startSecurity(){
return ResponseEntity.ok("Getting start with spring security");
}

}
