package net.sepidan.station.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.dto.UserCallLogAttributeRequest;
import net.sepidan.station.dto.UserCallLogAttributeResponse;
import net.sepidan.station.mapper.UserCallLogAttributeMapper;
import net.sepidan.station.persistent.domain.UserCallLogAttribute;
import net.sepidan.station.persistent.service.UserCallLogAttributeService;
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
@RequestMapping("/api/user-call-log-attributes")
@RequiredArgsConstructor
public class UserCallLogAttributeController {

  private final UserCallLogAttributeService userCallLogAttributeService;
  private final UserCallLogAttributeMapper userCallLogAttributeMapper;

  @PostMapping
  public ResponseEntity<UserCallLogAttributeResponse> create(
      @Valid @RequestBody UserCallLogAttributeRequest request) {
    UserCallLogAttribute created = userCallLogAttributeService.create(
        userCallLogAttributeMapper.mapToDomain(request));
    return ResponseEntity.ok(userCallLogAttributeMapper.mapToResponse(created));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserCallLogAttributeResponse> getById(@PathVariable String id) {
    UserCallLogAttribute found = userCallLogAttributeService.getSingleById(id);
    return ResponseEntity.ok(userCallLogAttributeMapper.mapToResponse(found));
  }


  @GetMapping
  public ResponseEntity<List<UserCallLogAttributeResponse>> getAll() {
    List<UserCallLogAttributeResponse> responses = userCallLogAttributeService.getAllUserCallLogAttribute()
        .stream()
        .map(userCallLogAttributeMapper::mapToResponse)
        .toList();
    return ResponseEntity.ok(responses);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserCallLogAttributeResponse> update(@PathVariable String id,
      @Valid @RequestBody UserCallLogAttributeRequest request) {
    UserCallLogAttribute userCallLogAttribute = userCallLogAttributeMapper.mapToDomain(request);
    var updated = userCallLogAttributeService.update(id, userCallLogAttribute);
    return ResponseEntity.ok(userCallLogAttributeMapper.mapToResponse(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    userCallLogAttributeService.softDelete(id);
    return ResponseEntity.noContent().build();
  }
}

