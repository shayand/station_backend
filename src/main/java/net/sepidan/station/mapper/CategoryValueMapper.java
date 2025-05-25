package net.sepidan.station.mapper;

import net.sepidan.station.dto.CategoryValueRequest;
import net.sepidan.station.dto.CategoryValueResponse;
import net.sepidan.station.persistent.domain.CategoryValue;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class CategoryValueMapper implements BaseDtoDomainMapper<CategoryValueRequest, CategoryValue> {

  @Override
  public CategoryValueRequest mapToDto(CategoryValue categoryValue) {
    if (categoryValue == null) return null;

    CategoryValueRequest dto = new CategoryValueRequest();
    dto.setCategoryId(categoryValue.getCategoryId());
    dto.setValue(categoryValue.getValue());
    dto.setLabel(categoryValue.getLabel());
    dto.setSortOrder(categoryValue.getSortOrder());
    return dto;
  }

  @Override
  public CategoryValue mapToDomain(CategoryValueRequest request) {
    if (request == null) return null;

    CategoryValue categoryValue = new CategoryValue();
    categoryValue.setId(UUID.randomUUID().toString());
    categoryValue.setCategoryId(request.getCategoryId());
    categoryValue.setValue(request.getValue());
    categoryValue.setLabel(request.getLabel());
    categoryValue.setSortOrder(request.getSortOrder());
    categoryValue.setCreatedAt(ZonedDateTime.now());
    return categoryValue;
  }

  public CategoryValueResponse mapToResponse(CategoryValue categoryValue) {
    if (categoryValue == null) return null;

    CategoryValueResponse response = new CategoryValueResponse();
    response.setId(categoryValue.getId());
    response.setCategoryId(categoryValue.getCategoryId());
    response.setValue(categoryValue.getValue());
    response.setLabel(categoryValue.getLabel());
    response.setSortOrder(categoryValue.getSortOrder());
    response.setCreatedAt(categoryValue.getCreatedAt());
    response.setUpdatedAt(categoryValue.getUpdatedAt());
    return response;
  }
}

