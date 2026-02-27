package com.mz.auth.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.mz.auth.entity.enums.UserRole;
import java.util.List;
import java.util.Collection;
import lombok.NoArgsConstructor;

@Entity
@Table(name="custom_user")
@NoArgsConstructor
public class CustomUser implements UserDetails{

public CustomUser(String username,String password,UserRole role){
this.username=username;
this. password=password;
this.role=role;
}

@Id
@GeneratedValue(strategy=GenerationType.UUID)
 private String id;

@Column(nullable=false,unique=true)
 private String username;

@Column(nullable=false)
 private String password;

@Column(nullable=false)
@Enumerated(EnumType.STRING)
private UserRole role;

@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
if(this.role==UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
}

@Override
public String getUsername(){
return username;
}

@Override
public String getPassword(){
return password;
}

public UserRole getRole(){
return role;
}


   @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
