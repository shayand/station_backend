package net.sepidan.station.dto;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;
import net.sepidan.station.enums.CallOutcome;

@Data
public class UserCallLogResponse {

  private String id;
  private UUID userId;
  private String phone;
  private Integer callDurationSeconds;
  private String message;
  private CallOutcome callOutcome;
  private String employeeId;
  private String employeeFullName;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}

