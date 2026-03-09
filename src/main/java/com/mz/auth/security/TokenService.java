package com.mz.auth.security;
import com.mz.auth.entity.CustomUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Duration;


@Service
public class TokenService{

@Value("${security.jwt.secret-key}")
private String secret;


private Instant generateExpirationDate(){
return Instant.now().plus(Duration.ofHours(2));
}


public String generateToken(CustomUser user){
try{
Algorithm algorithm = Algorithm.HMAC256(secret);
String token =JWT.create()
.withIssuer("auth-api")
.withSubject(user.getUsername())
.withExpiresAt(generateExpirationDate())
.sign(algorithm);

return token;

} catch(JWTCreationException ex){

throw new RuntimeException("error while generating token",ex);

}
}


public boolean isTokenValid(String token){
if(token==null||token.isEmpty())return false;

try{
Algorithm algorithm = Algorithm.HMAC256(secret);

JWT.require(algorithm)
.withIssuer("auth-api")
.build()
.verify(token);

return true;
}catch(JWTVerificationException ex){
return false;
}

}


public String getSubject(String token){
if(token==null||token.isEmpty()){
return " ";
}

return JWT.require(Algorithm.HMAC256(secret))
.withIssuer("auth-api")
.build()
.verify(token)
.getSubject();
}





}
