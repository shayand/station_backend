package net.sepidan.station.dto;


import java.util.UUID;
import lombok.Data;
import net.sepidan.station.enums.CallOutcome;

@Data
public class UserCallLogRequest {

  private UUID userId;
  private String phone;
  private Integer callDurationSeconds;
  private String message;
  private CallOutcome callOutcome;
  private String employeeId;
}

