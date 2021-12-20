package com.relevel.backend.service;

import com.relevel.backend.dto.User;
import com.relevel.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;


  public String registerUser(User user) {

    userRepository.save(user);

    return "Successful";
  }
}
