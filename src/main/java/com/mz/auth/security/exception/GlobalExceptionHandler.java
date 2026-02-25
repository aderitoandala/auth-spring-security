package com.mz.auth.security.exception;
import com.mz.auth.security.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import jakarta. servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import com.mz.auth.dto.ApiErrorDetails;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

@ExceptionHandler(UserAlreadyExistsException.class)
public ResponseEntity<ApiErrorDetails> userAlreadyExistsExceptionHandler(UserAlreadyExistsException ex,HttpServletRequest request ){
log.warn("user already exists.path:{}",request.getRequestURI(),ex);

HttpStatus status = HttpStatus.CONFLICT;
ApiErrorDetails errorDetails= new ApiErrorDetails(status.value(),status.getReasonPhrase(),ex.getMessage(),request.getRequestURI());
return ResponseEntity.status(status).body(errorDetails);

}


}
