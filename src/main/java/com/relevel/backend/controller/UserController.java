package com.relevel.backend.controller;


import com.relevel.backend.config.JWTUtility;
import com.relevel.backend.dto.User;
import com.relevel.backend.exchanges.GetUserRequest;
import com.relevel.backend.exchanges.LoginRequest;
import com.relevel.backend.exchanges.LoginResponse;
import com.relevel.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final String USER_ENDPOINT = "/api/users";
  private final String REGISTER_ENDPOINT = "/register";
  private final String LOGIN_ENDPOINT = "/login";

  @Autowired
  private UserService userService;

  @Autowired
  private JWTUtility jwtUtility;

  @Autowired
  private AuthenticationManager authenticationManager;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(USER_ENDPOINT + REGISTER_ENDPOINT)
  public ResponseEntity postRegisterUser(@RequestBody User user) {
    userService.registerUser(user);
    return ResponseEntity.ok("Successful");
  }

  @PostMapping(USER_ENDPOINT + LOGIN_ENDPOINT)
  public ResponseEntity postLoginUser(@RequestBody LoginRequest loginRequest) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getEmail(),
              loginRequest.getPassword()
          )
      );
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }

    final UserDetails userDetails
        = userService.loadUserByUsername(loginRequest.getEmail());

    final String token =
        jwtUtility.generateToken(userDetails);

    return ResponseEntity.ok(new LoginResponse(token));
  }

  @GetMapping(USER_ENDPOINT)
  public ResponseEntity<User> getUserByName(GetUserRequest getUserRequest) {
    getUserRequest.toString();
    User user = userService.findUserByName(getUserRequest.getName());

    return ResponseEntity.ok(user);
  }


}
