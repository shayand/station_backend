package net.sepidan.station.persistent.service;

import java.util.List;
import net.sepidan.station.persistent.domain.Category;

public interface CategoryService {

  // Create
  public Category createCategory(Category category);

  // Read (exclude soft-deleted)
  public List<Category> getAllCategories();

  // Read by ID (exclude soft-deleted)
  public Category getCategoryById(String id);

  // Update
  public Category updateCategory(String id, Category updatedCategory);

  // Soft Delete
  public void softDeleteCategory(String id);

}
