package com.mz.auth.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import com.mz.auth.entity.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser,String>{

Optional<UserDetails> findByUsername(String username);
boolean existsByUsername(String username);

}
