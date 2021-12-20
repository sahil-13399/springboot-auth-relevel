package com.relevel.backend.controller;


import com.relevel.backend.dto.User;
import com.relevel.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final String USER_ENDPOINT = "/api/users";
  private final String REGISTER_ENDPOINT = "/register";

  @Autowired
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(USER_ENDPOINT + REGISTER_ENDPOINT)
  public ResponseEntity postRegisterUser(@RequestBody User user) {
    userService.registerUser(user);
    return ResponseEntity.ok("Successful");
  }


}
