package net.sepidan.station.mapper;

import net.sepidan.station.dto.AttributeRequest;
import net.sepidan.station.dto.AttributeResponse;
import net.sepidan.station.persistent.domain.Attribute;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;
import org.springframework.stereotype.Component;

@Component
public class AttributeMapper implements BaseDtoDomainMapper<AttributeRequest, Attribute> {

  @Override
  public AttributeRequest mapToDto(Attribute attribute) {
    if (attribute == null) {
      return null;
    }
    AttributeRequest dto = new AttributeRequest();
    dto.setFaName(attribute.getFaName());
    dto.setEnName(attribute.getEnName());
    dto.setDescription(attribute.getDescription());
    dto.setAttributeType(attribute.getAttributeType() != null ? attribute.getAttributeType() : null);
    return dto;
  }

  @Override
  public Attribute mapToDomain(AttributeRequest request) {
    if (request == null) {
      return null;
    }
    Attribute domain = new Attribute();
    domain.setFaName(request.getFaName());
    domain.setEnName(request.getEnName());
    domain.setDescription(request.getDescription());
    if (request.getAttributeType() != null) {
      var type = new AttributeDataBaseType();
      type.setId(String.valueOf(request.getAttributeType()));
      domain.setAttributeType(type);
    }
    return domain;
  }

  public AttributeResponse mapToResponse(Attribute attribute) {
    if (attribute == null) {
      return null;
    }
    AttributeResponse response = new AttributeResponse();
    response.setId(attribute.getId());
    response.setFaName(attribute.getFaName());
    response.setEnName(attribute.getEnName());
    response.setDescription(attribute.getDescription());
    response.setAttributeType(attribute.getAttributeType() != null ? attribute.getAttributeType() : null);
    response.setCreatedAt(attribute.getCreatedAt());
    response.setUpdatedAt(attribute.getUpdatedAt());
    return response;
  }
}