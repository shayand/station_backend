package net.sepidan.station.dto;

import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class EmployeeResponse {

  private String id;
  private String firstName;
  private String lastName;
  private String phone;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}
