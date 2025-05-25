package net.sepidan.station.mapper;

import net.sepidan.station.dto.CategoryRequest;
import net.sepidan.station.dto.CategoryResponse;
import net.sepidan.station.persistent.domain.Category;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class CategoryMapper implements BaseDtoDomainMapper<CategoryRequest, Category> {

  @Override
  public CategoryRequest mapToDto(Category category) {
    if (category == null) {
      return null;
    }
    CategoryRequest dto = new CategoryRequest();
    dto.setCategoryName(category.getCategoryName());
    dto.setDescription(category.getDescription());
    return dto;
  }

  @Override
  public Category mapToDomain(CategoryRequest request) {
    if (request == null) {
      return null;
    }
    Category category = new Category();
    category.setId(UUID.randomUUID().toString()); // or let Elasticsearch assign ID
    category.setCategoryName(request.getCategoryName());
    category.setDescription(request.getDescription());
    category.setCreatedAt(ZonedDateTime.now());
    return category;
  }

  public CategoryResponse mapToResponse(Category category) {
    if (category == null) {
      return null;
    }
    CategoryResponse response = new CategoryResponse();
    response.setId(category.getId());
    response.setCategoryName(category.getCategoryName());
    response.setDescription(category.getDescription());
    response.setCreatedAt(category.getCreatedAt());
    response.setUpdatedAt(category.getUpdatedAt());
    return response;
  }
}
