package com.relevel.backend.dto;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private List<Book> books;

}
