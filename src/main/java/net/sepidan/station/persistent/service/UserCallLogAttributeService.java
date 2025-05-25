package net.sepidan.station.persistent.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.persistent.domain.UserCallLogAttribute;
import net.sepidan.station.persistent.repository.UserCallLogAttributeRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserCallLogAttributeService {

  private final UserCallLogAttributeRepository userCallLogAttributeRepository;

  // Create
  public UserCallLogAttribute create(UserCallLogAttribute entity) {
    return userCallLogAttributeRepository.save(entity);
  }

  // Read all (non-deleted)
  public List<UserCallLogAttribute> getAllUserCallLogAttribute() {
    return userCallLogAttributeRepository.findAllByDeletedAtIsNull();
  }

  // Read one (non-deleted)
  public UserCallLogAttribute getSingleById(String id) {
    return userCallLogAttributeRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new NoSuchElementException(
            "UserCallLogAttribute not found or deleted with ID: " + id));
  }

  // Update (non-deleted only)
  public UserCallLogAttribute update(String id, UserCallLogAttribute updated) {
    return userCallLogAttributeRepository.findByIdAndDeletedAtIsNull(id)
        .map(existing -> {
          existing.setAttribute(updated.getAttribute());
          existing.setCategory(updated.getCategory());
          existing.setCategoryValue(updated.getCategoryValue());
          existing.setValueText(updated.getValueText());
          existing.setValueInteger(updated.getValueInteger());
          existing.setValueDecimal(updated.getValueDecimal());
          existing.setValueBoolean(updated.getValueBoolean());
          existing.setValueDate(updated.getValueDate());
          existing.setValueTimestamp(updated.getValueTimestamp());
          existing.setValueTimestamptz(updated.getValueTimestamptz());
          return userCallLogAttributeRepository.save(existing);
        })
        .orElseThrow(() -> new NoSuchElementException(
            "UserCallLogAttribute not found or deleted with ID: " + id));
  }

  // Soft Delete
  public void softDelete(String id) {
    userCallLogAttributeRepository.findByIdAndDeletedAtIsNull(id)
        .ifPresent(entity -> {
          entity.setDeletedAt(ZonedDateTime.now());
          userCallLogAttributeRepository.save(entity);
          log.info("Soft deleted UserCallLogAttribute with ID: {}", id);
        });
  }

}
