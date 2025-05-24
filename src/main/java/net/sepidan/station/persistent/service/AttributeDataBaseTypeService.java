package net.sepidan.station.persistent.service;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.enums.DatabaseType;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;
import net.sepidan.station.persistent.repository.AttributeDataBaseTypeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeDataBaseTypeService {

  private final AttributeDataBaseTypeRepository attributeDataBaseTypeRepository;

  public AttributeDataBaseType findById(String id) {
    return attributeDataBaseTypeRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("AttributeDataBaseType with ID: " + id));
  }

  public AttributeDataBaseType save(AttributeDataBaseType attributeDataBaseType) {
    return attributeDataBaseTypeRepository.save(attributeDataBaseType);
  }

  public Long count() {
    return attributeDataBaseTypeRepository.count();
  }


  public AttributeDataBaseType create(AttributeDataBaseType attributeDataBaseType) {
    return attributeDataBaseTypeRepository.save(attributeDataBaseType);
  }

  public AttributeDataBaseType update(String id, DatabaseType newType) {
    return attributeDataBaseTypeRepository.findById(id)
        .map(existing -> {
          existing.setDatabaseType(newType);
          return attributeDataBaseTypeRepository.save(existing); // updatedAt auto
        })
        .orElseThrow(() -> new NoSuchElementException("Not found: " + id));
  }
}
