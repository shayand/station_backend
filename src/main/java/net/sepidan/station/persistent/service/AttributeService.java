package net.sepidan.station.persistent.service;

import io.netty.util.ResourceLeakException;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.persistent.domain.Attribute;
import net.sepidan.station.persistent.repository.AttributeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttributeService {

  private final AttributeRepository attributeRepository;

  // Create
  public Attribute save(Attribute attribute) {
    log.info("Creating attribute: {}", attribute);
    return attributeRepository.save(attribute);
  }

  // Read (by ID)
  public @NotNull Attribute getAttributeById(String id) {
    log.info("Fetching attribute with ID: {}", id);
    return attributeRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NoSuchElementException(
        "Attribute not found or deleted with ID: " + id));
  }

  // Read (all)
  public List<Attribute> getAllAttributes() {
    log.info("Fetching all attributes");
    return attributeRepository.findAllByDeletedAtIsNull();
  }

  // Update
  public Attribute updateAttribute(String id, Attribute updatedAttribute) {
    log.info("Updating attribute with ID: {}", id);
    return attributeRepository.findByIdAndDeletedAtIsNull(id)
        .map(existing -> {
          existing.setFaName(updatedAttribute.getFaName());
          existing.setEnName(updatedAttribute.getEnName());
          existing.setAttributeType(updatedAttribute.getAttributeType());
          existing.setDescription(updatedAttribute.getDescription());
          // the updatedAt Automatically updates
          return attributeRepository.save(existing);
        })
        .orElseThrow(() -> new NoSuchElementException("Attribute not found with ID: " + id));
  }

  // Delete (soft or hard)
  public void softDelete(String id) {
    Attribute attribute = attributeRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new NoSuchElementException("Attribute not found with ID: " + id));
    attribute.setDeletedAt(ZonedDateTime.now());
    log.info("soft deleting attribute with ID: {}", id);
    attributeRepository.save(attribute);
  }


}
