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
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  // Create
  @Override
  public Category createCategory(Category category) {
    log.info("Creating new category");
    return categoryRepository.save(category);
  }

  // Read (exclude soft-deleted)
  @Override
  public List<Category> getAllCategories() {
    log.info("Fetching all non-deleted categories");
    return categoryRepository.findAllByDeletedAtIsNull();
  }

  // Read by ID (exclude soft-deleted)
  @Override
  public Category getCategoryById(String id) {
    log.info("Fetching category by ID: {}", id);
    return categoryRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + id));
  }

  // Update
  @Override
  public Category updateCategory(String id, Category updatedCategory) {
    log.info("Updating category with ID: {}", id);
    return categoryRepository.findByIdAndDeletedAtIsNull(id)
        .map(existing -> {
          existing.setCategoryName(updatedCategory.getCategoryName());
          existing.setDescription(updatedCategory.getDescription());
          // the updatedAt Automatically updates
          return categoryRepository.save(existing);
        })
        .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + id));
  }

  // Soft Delete
  @Override
  public void softDeleteCategory(String id) {
    log.info("Soft deleting category with ID: {}", id);
    categoryRepository.findByIdAndDeletedAtIsNull(id)
        .ifPresent(existing -> {
          existing.setDeletedAt(ZonedDateTime.now());
          categoryRepository.save(existing); // triggers BaseAuditableCallback
        });
  }
}
