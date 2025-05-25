package net.sepidan.station.persistent.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.enums.DatabaseType;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;
import net.sepidan.station.persistent.repository.AttributeDataBaseTypeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeDataBaseTypeServiceImpl implements AttributeDataBaseTypeService {

  private final AttributeDataBaseTypeRepository attributeDataBaseTypeRepository;

  @Override
  public List<AttributeDataBaseType> getAll() {
    return (List<AttributeDataBaseType>) attributeDataBaseTypeRepository.findAll();
  }
  @Override
  public AttributeDataBaseType findById(String id) {
    return attributeDataBaseTypeRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("AttributeDataBaseType with ID: " + id));
  }

  @Override
  public AttributeDataBaseType save(AttributeDataBaseType attributeDataBaseType) {
    return attributeDataBaseTypeRepository.save(attributeDataBaseType);
  }

  @Override
  public Long count() {
    return attributeDataBaseTypeRepository.count();
  }

  @Override
  public AttributeDataBaseType create(AttributeDataBaseType attributeDataBaseType) {
    return attributeDataBaseTypeRepository.save(attributeDataBaseType);
  }
  @Override
  public AttributeDataBaseType update(String id, DatabaseType newType) {
    return attributeDataBaseTypeRepository.findById(id)
        .map(existing -> {
          existing.setDatabaseType(newType);
          return attributeDataBaseTypeRepository.save(existing); // updatedAt auto
        })
        .orElseThrow(() -> new NoSuchElementException("Not found: " + id));
  }

}
