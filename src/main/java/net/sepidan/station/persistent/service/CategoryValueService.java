package net.sepidan.station.persistent.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.persistent.domain.CategoryValue;
import net.sepidan.station.persistent.repository.CategoryValueRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryValueService {


  private final CategoryValueRepository categoryValueRepository;

  // Create
  public CategoryValue createCategoryValue(CategoryValue categoryValue) {
    log.info("Creating new CategoryValue");
    return categoryValueRepository.save(categoryValue);
  }

  // Read (all non-deleted)
  public List<CategoryValue> getAllActiveCategoryValues() {
    return categoryValueRepository.findAllByDeletedAtIsNull();
  }

  // Read one
  public CategoryValue getCategoryValueById(String id) {
    return categoryValueRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(
            () -> new NoSuchElementException("CategoryValue not found or deleted with ID: " + id));
  }

  // Update
  public CategoryValue updateCategoryValue(String id, CategoryValue updated) {
    return categoryValueRepository.findByIdAndDeletedAtIsNull(id)
        .map(existing -> {
          existing.setCategoryId(updated.getCategoryId());
          existing.setValue(updated.getValue());
          existing.setLabel(updated.getLabel());
          existing.setSortOrder(updated.getSortOrder());
          return categoryValueRepository.save(existing);
        })
        .orElseThrow(
            () -> new NoSuchElementException("CategoryValue not found or deleted with ID: " + id));
  }

  // Soft Delete
  public void SoftDelete(String id) {
    categoryValueRepository.findByIdAndDeletedAtIsNull(id)
        .ifPresent(existing -> {
          existing.setDeletedAt(ZonedDateTime.now());
          categoryValueRepository.save(existing);
          log.info("Soft deleted CategoryValue with ID: {}", id);
        });
  }


}
