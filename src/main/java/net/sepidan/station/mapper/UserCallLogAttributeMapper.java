package net.sepidan.station.mapper;

import net.sepidan.station.dto.UserCallLogAttributeRequest;
import net.sepidan.station.dto.UserCallLogAttributeResponse;
import net.sepidan.station.persistent.domain.UserCallLogAttribute;
import net.sepidan.station.persistent.service.AttributeService;

import net.sepidan.station.persistent.service.CategoryService;
import net.sepidan.station.persistent.service.CategoryValueService;
import net.sepidan.station.persistent.service.UserCallLogService;
import org.springframework.stereotype.Component;

@Component
public class UserCallLogAttributeMapper implements
    BaseDtoDomainMapper<UserCallLogAttributeRequest, UserCallLogAttribute> {

  private final CategoryService categoryService;
  private final CategoryValueService categoryValueService;
  private final AttributeService attributeService;
  private final UserCallLogService userCallLogService;

  public UserCallLogAttributeMapper(CategoryService categoryService,
      CategoryValueService categoryValueService, AttributeService attributeService,
      UserCallLogService userCallLogService) {
    this.categoryService = categoryService;
    this.categoryValueService = categoryValueService;
    this.attributeService = attributeService;
    this.userCallLogService = userCallLogService;
  }


  @Override
  public UserCallLogAttributeRequest mapToDto(UserCallLogAttribute entity) {
    if (entity == null) {
      return null;
    }

    UserCallLogAttributeRequest dto = new UserCallLogAttributeRequest();
    dto.setUserCallLogId(entity.getUserCallLog().getId());
    dto.setAttributeId(entity.getAttribute().getId());
    dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
    dto.setCategoryValueId(
        entity.getCategoryValue() != null ? entity.getCategoryValue().getId() : null);

    dto.setValueText(entity.getValueText());
    dto.setValueInteger(entity.getValueInteger());
    dto.setValueDecimal(entity.getValueDecimal());
    dto.setValueBoolean(entity.getValueBoolean());
    dto.setValueDate(entity.getValueDate());
    dto.setValueTimestamp(entity.getValueTimestamp());
    dto.setValueTimestamptz(entity.getValueTimestamptz());

    return dto;
  }

  @Override
  public UserCallLogAttribute mapToDomain(UserCallLogAttributeRequest request) {
    if (request == null) {
      return null;
    }

    UserCallLogAttribute entity = new UserCallLogAttribute();
    entity.setUserCallLog(userCallLogService.getUserCallLogById(request.getUserCallLogId()));
    entity.setAttribute(attributeService.getAttributeById(request.getAttributeId()));
    if (request.getCategoryId() != null) {
      entity.setCategory(categoryService.getCategoryById(request.getCategoryId()));
    }
    if (request.getCategoryValueId() != null) {
      entity.setCategoryValue(
          categoryValueService.getCategoryValueById(request.getCategoryValueId()));
    }

    entity.setValueText(request.getValueText());
    entity.setValueInteger(request.getValueInteger());
    entity.setValueDecimal(request.getValueDecimal());
    entity.setValueBoolean(request.getValueBoolean());
    entity.setValueDate(request.getValueDate());
    entity.setValueTimestamp(request.getValueTimestamp());
    entity.setValueTimestamptz(request.getValueTimestamptz());

    return entity;
  }

  public UserCallLogAttributeResponse mapToResponse(UserCallLogAttribute entity) {
    if (entity == null) {
      return null;
    }

    UserCallLogAttributeResponse dto = new UserCallLogAttributeResponse();
    dto.setId(entity.getId());
    dto.setUserCallLogId(entity.getUserCallLog().getId());
    dto.setAttributeId(entity.getAttribute().getId());
    dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
    dto.setCategoryValueId(
        entity.getCategoryValue() != null ? entity.getCategoryValue().getId() : null);

    dto.setValueText(entity.getValueText());
    dto.setValueInteger(entity.getValueInteger());
    dto.setValueDecimal(entity.getValueDecimal());
    dto.setValueBoolean(entity.getValueBoolean());
    dto.setValueDate(entity.getValueDate());
    dto.setValueTimestamp(entity.getValueTimestamp());
    dto.setValueTimestamptz(entity.getValueTimestamptz());

    dto.setCreatedAt(entity.getCreatedAt());
    dto.setUpdatedAt(entity.getUpdatedAt());

    return dto;
  }
}

