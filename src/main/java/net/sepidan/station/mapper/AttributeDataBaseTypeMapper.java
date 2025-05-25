package net.sepidan.station.mapper;

import net.sepidan.station.dto.AttributeDataBaseTypeRequest;
import net.sepidan.station.dto.AttributeDataBaseTypeResponse;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;
import org.springframework.stereotype.Component;

@Component
public class AttributeDataBaseTypeMapper implements
    BaseDtoDomainMapper<AttributeDataBaseTypeRequest, AttributeDataBaseType> {

  @Override
  public AttributeDataBaseTypeRequest mapToDto(AttributeDataBaseType attributeDataBaseType) {
    if (attributeDataBaseType == null) {
      return null;
    }
    AttributeDataBaseTypeRequest request = new AttributeDataBaseTypeRequest();
    request.setId(attributeDataBaseType.getId());
    request.setDatabaseType(attributeDataBaseType.getDatabaseType());
    return request;
  }

  @Override
  public AttributeDataBaseType mapToDomain(AttributeDataBaseTypeRequest request) {
    if (request == null) {
      return null;
    }
    AttributeDataBaseType domain = new AttributeDataBaseType();
    domain.setId(request.getId());
    domain.setDatabaseType(request.getDatabaseType());
    return domain;
  }

  public AttributeDataBaseTypeResponse mapToResponse(AttributeDataBaseType domain) {
    if (domain == null) {
      return null;
    }
    AttributeDataBaseTypeResponse response = new AttributeDataBaseTypeResponse();
    response.setId(domain.getId());
    response.setDatabaseType(domain.getDatabaseType());
    return response;
  }
}