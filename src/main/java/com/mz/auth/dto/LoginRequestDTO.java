package com.mz.auth.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
@NotBlank(message = "The username is required") String username,
@NotBlank(message = "The password is required") @Size(min = 8, message = "The password must have at least 8 characters")String password){}














