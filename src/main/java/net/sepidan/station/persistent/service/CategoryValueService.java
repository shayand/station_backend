package net.sepidan.station.persistent.service;


import java.util.List;
import net.sepidan.station.persistent.domain.CategoryValue;

public interface CategoryValueService {

  // Create
  public CategoryValue createCategoryValue(CategoryValue categoryValue);

  // Read (all non-deleted)
  public List<CategoryValue> getAllActiveCategoryValues();

  // Read one
  public CategoryValue getCategoryValueById(String id);

  // Update
  public CategoryValue updateCategoryValue(String id, CategoryValue updated);

  // Soft Delete
  public void SoftDelete(String id);

}