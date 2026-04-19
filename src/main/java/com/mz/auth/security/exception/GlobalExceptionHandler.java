package com.mz.auth.security.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import jakarta. servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import com.mz.auth.dto.ApiErrorDetails;
import com.mz.auth.dto.FieldsValidationErrorDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Map;
import java.util.HashMap;

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


@ExceptionHandler(TokenGenerationException.class)
public ResponseEntity<ApiErrorDetails> tokenGenerationHandler(TokenGenerationException ex,HttpServletRequest request ){
log.error("error while generate token.path:{}",request.getRequestURI(),ex);

HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
ApiErrorDetails errorDetails= new ApiErrorDetails(status.value(),status.getReasonPhrase(),"Login failed.Please try again later!",request.getRequestURI());
return ResponseEntity.status(status).body(errorDetails);
}


@ExceptionHandler(UsernameNotFoundException.class)
public ResponseEntity<ApiErrorDetails> usernameNotFoundHandler(UsernameNotFoundException ex,HttpServletRequest request ){
log.warn("user not found.path:{}",request.getRequestURI(),ex);

HttpStatus status = HttpStatus.UNAUTHORIZED;
ApiErrorDetails errorDetails= new ApiErrorDetails(status.value(),status.getReasonPhrase(),"Invalid credentials",request.getRequestURI());
return ResponseEntity.status(status).body(errorDetails);
}

@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<ApiErrorDetails> badCredentialsHandler(BadCredentialsException ex, HttpServletRequest request) {
    log.warn("authentication failed.path:{}", request.getRequestURI());
    
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    ApiErrorDetails errorDetails = new ApiErrorDetails(status.value(),status.getReasonPhrase(),"Invalid credentials",request.getRequestURI());
    return ResponseEntity.status(status).body(errorDetails);
}


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<FieldsValidationErrorDetails>
fieldsValidationHandler(MethodArgumentNotValidException ex , HttpServletRequest request){
log.warn("field validation error in path:{}",request.getRequestURI(),ex);

HttpStatus status= HttpStatus.BAD_REQUEST;
Map<String,String> fields=new HashMap<>();
ex.getBindingResult().getFieldErrors().forEach(field -> fields.put(field.getField(),
field.getDefaultMessage()));

return
ResponseEntity.status(status).body(
new FieldsValidationErrorDetails(status.value(),
status.getReasonPhrase(),
"One or more fields have failed validation",
fields,
request.getRequestURI()));
}

}
