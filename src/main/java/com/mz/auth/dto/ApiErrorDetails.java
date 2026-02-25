package com.mz.auth.dto;
import java.time.LocalDateTime;

public record ApiErrorDetails(
int status,
String title,
String message,
LocalDateTime timestamp,
String path){

public ApiErrorDetails(int status,String title,String message,String path){
this(status,title,message,LocalDateTime.now(),path);

}

}
