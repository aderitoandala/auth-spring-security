package com.mz.auth.repository;
import com.mz.auth.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<CustomUser,Long>{

Optional<CustomUser> findByUsername(String username);

}
