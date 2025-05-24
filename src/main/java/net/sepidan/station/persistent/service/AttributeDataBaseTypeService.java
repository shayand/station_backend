package net.sepidan.station.persistent.service;

import lombok.RequiredArgsConstructor;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;
import net.sepidan.station.persistent.repository.AttributeDataBaseTypeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeDataBaseTypeService {

  private final AttributeDataBaseTypeRepository attributeDataBaseTypeRepository;

  public AttributeDataBaseType findById(String id) {
    return attributeDataBaseTypeRepository.findById(id).orElse(null);
  }

  public AttributeDataBaseType save(AttributeDataBaseType attributeDataBaseType) {
    return attributeDataBaseTypeRepository.save(attributeDataBaseType);
  }

  public Long count() {
    return attributeDataBaseTypeRepository.count();
  }

}
