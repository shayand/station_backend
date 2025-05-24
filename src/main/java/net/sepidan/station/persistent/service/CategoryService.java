package net.sepidan.station.persistent.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.persistent.domain.Category;
import net.sepidan.station.persistent.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

  private final CategoryRepository categoryRepository;

  // Create
  public Category createCategory(Category category) {
    log.info("Creating new category");
    return categoryRepository.save(category);
  }

  // Read (exclude soft-deleted)
  public List<Category> getAllCategories() {
    log.info("Fetching all non-deleted categories");
    return categoryRepository.findAllByDeletedAtIsNotNull();
  }

  // Read by ID (exclude soft-deleted)
  public Category getCategoryById(String id) {
    log.info("Fetching category by ID: {}", id);
    return categoryRepository.findById(id)
        .filter(c -> c.getDeletedAt() == null)
        .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + id));
  }

  // Update
  public Category updateCategory(String id, Category updatedCategory) {
    log.info("Updating category with ID: {}", id);
    return categoryRepository.findById(id)
        .map(existing -> {
          existing.setCategoryName(updatedCategory.getCategoryName());
          existing.setDescription(updatedCategory.getDescription());
          // the updatedAt Automatically updates
          return categoryRepository.save(existing);
        })
        .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + id));
  }

  // Soft Delete
  public void softDeleteCategory(String id) {
    log.info("Soft deleting category with ID: {}", id);
    categoryRepository.findById(id)
        .ifPresent(existing -> {
          existing.setDeletedAt(ZonedDateTime.now());
          categoryRepository.save(existing); // triggers BaseAuditableCallback
        });
  }
}
