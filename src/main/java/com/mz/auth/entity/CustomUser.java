package com.mz.auth.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="custom_user")
public class CustomUser implements UserDetails{

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;

@Column(nullable=false,unique=true)
 private String username;

@Column(nullable=false)
 private String password;

@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
return new ArrayList<>();
}

@Override
public String getUsername(){
return username;
}

@Override
public String getPassword(){
return password;
}

}
