package net.sepidan.station.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String phone;
}
