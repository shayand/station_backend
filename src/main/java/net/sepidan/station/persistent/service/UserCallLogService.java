package net.sepidan.station.persistent.service;

import java.util.List;
import net.sepidan.station.persistent.domain.UserCallLog;

public interface UserCallLogService {
  // Create
  public UserCallLog createUserCallLog(UserCallLog userCallLog);

  // Read all (non-deleted)
  public List<UserCallLog> getAllUserCallLogs() ;

  // Read by ID (non-deleted)
  public UserCallLog getUserCallLogById(String id);

  // Update
  public UserCallLog updateUserCallLog(String id, UserCallLog updatedLog);

  // Soft delete
  public void softDeleteUserCallLog(String id);
}
