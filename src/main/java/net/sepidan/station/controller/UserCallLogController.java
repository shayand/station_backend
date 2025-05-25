package net.sepidan.station.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.dto.UserCallLogRequest;
import net.sepidan.station.dto.UserCallLogResponse;
import net.sepidan.station.mapper.UserCallLogMapper;
import net.sepidan.station.persistent.domain.UserCallLog;
import net.sepidan.station.persistent.service.UserCallLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-call-logs")
@RequiredArgsConstructor
public class UserCallLogController {

  private final UserCallLogService userCallLogService;
  private final UserCallLogMapper userCallLogMapper;

  @GetMapping("/{id}")
  public ResponseEntity<UserCallLogResponse> getById(@PathVariable String id) {
    UserCallLog found = userCallLogService.getUserCallLogById(id);
    return ResponseEntity.ok(userCallLogMapper.mapToResponse(found));
  }

  @GetMapping
  public List<UserCallLogResponse> getAll() {
    return userCallLogService.getAllUserCallLogs().stream().map(userCallLogMapper::mapToResponse)
        .toList();
  }

  @PostMapping
  public ResponseEntity<UserCallLogResponse> create(@RequestBody UserCallLogRequest request) {
    var saved = userCallLogService.createUserCallLog(userCallLogMapper.mapToDomain(request));
    return ResponseEntity.ok(userCallLogMapper.mapToResponse(saved));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserCallLogResponse> update(@PathVariable String id,
      @RequestBody UserCallLogRequest request) {
    UserCallLog userCallLog = userCallLogMapper.mapToDomain(request);
    UserCallLog updated = userCallLogService.updateUserCallLog(id, userCallLog);
    return ResponseEntity.ok(userCallLogMapper.mapToResponse(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    userCallLogService.softDeleteUserCallLog(id);
    return ResponseEntity.noContent().build();
  }
}

