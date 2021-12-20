package com.relevel.backend.dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  @NotBlank
  private String name;
  @NotBlank
  private String password;
  @NotBlank
  private String phone;
  @Email
  @NotBlank
  private String email;
  private int age;
  @NotBlank
  private String gender;
  @NotBlank
  private String address;

}
