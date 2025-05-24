package net.sepidan.station.persistent.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.persistent.domain.UserCallLog;
import net.sepidan.station.persistent.repository.UserCallLogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCallLogService {

  private final UserCallLogRepository userCallLogRepository;

  // Create
  public UserCallLog createUserCallLog(UserCallLog userCallLog) {
    log.info("Creating new UserCallLog");
    return userCallLogRepository.save(userCallLog);
  }

  // Read all (non-deleted)
  public List<UserCallLog> getAllUserCallLogs() {
    log.info("Fetching all non-deleted UserCallLogs");
    return userCallLogRepository.findAllByDeletedAtIsNull();
  }

  // Read by ID (non-deleted)
  public UserCallLog getUserCallLogById(String id) {
    log.info("Fetching UserCallLog by ID: {}", id);
    return userCallLogRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new NoSuchElementException("UserCallLog not found with ID: " + id));
  }

  // Update
  public UserCallLog updateUserCallLog(String id, UserCallLog updatedLog) {
    log.info("Updating UserCallLog with ID: {}", id);
    return userCallLogRepository.findByIdAndDeletedAtIsNull(id)
        .map(existing -> {
          existing.setPhone(updatedLog.getPhone());
          existing.setCallDurationSeconds(updatedLog.getCallDurationSeconds());
          existing.setMessage(updatedLog.getMessage());
          existing.setCallOutcome(updatedLog.getCallOutcome());
          // updatedAt should be auto-handled by audit callbacks
          return userCallLogRepository.save(existing);
        })
        .orElseThrow(() -> new NoSuchElementException("UserCallLog not found with ID: " + id));
  }

  // Soft delete
  public void softDeleteUserCallLog(String id) {
    log.info("Soft deleting UserCallLog with ID: {}", id);
    userCallLogRepository.findByIdAndDeletedAtIsNull(id)
        .ifPresent(existing -> {
          existing.setDeletedAt(ZonedDateTime.now());
          userCallLogRepository.save(existing);
        });
  }
}

