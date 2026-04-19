package com.mz.auth.dto;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

public class FieldsValidationErrorDetails{

private int status;
private String title;
private String message;
private LocalDateTime timestamp;
private Map<String,String> fields;
private String path;

public FieldsValidationErrorDetails(int status,String title,String message,Map<String,String>fields,String path){
this.timestamp=LocalDateTime.now();
this.fields=fields;
this.path=path;
this.status=status;
this.title=title;
this.message=message;
}


public Map<String,String> getFields(){
return fields;
}

public String getPath(){
return path;
}

public LocalDateTime getTimestamp(){
return timestamp;
}

public int getStatus(){
return status;
}

public String getMessage(){
return message;
}

public String getTitle(){
return title;
}





}













