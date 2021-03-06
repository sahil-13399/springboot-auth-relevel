package com.relevel.backend.repository;

import com.relevel.backend.dto.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  User findByName(String name);
}
