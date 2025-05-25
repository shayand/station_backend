package net.sepidan.station.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.dto.CategoryRequest;
import net.sepidan.station.dto.CategoryResponse;
import net.sepidan.station.mapper.CategoryMapper;
import net.sepidan.station.persistent.domain.Category;
import net.sepidan.station.persistent.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;
  private final CategoryMapper categoryMapper;

  @PostMapping
  public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
    Category category = categoryMapper.mapToDomain(request);
    Category saved = categoryService.createCategory(category);
    return ResponseEntity.ok(categoryMapper.mapToResponse(saved));
  }


  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponse> update(@PathVariable String id,
      @RequestBody CategoryRequest request) {
    Category category = categoryMapper.mapToDomain(request);
    var updated = categoryService.updateCategory(id, category);
    return ResponseEntity.ok(categoryMapper.mapToResponse(updated));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getById(@PathVariable String id) {
    Category found = categoryService.getCategoryById(id);
    return ResponseEntity.ok(categoryMapper.mapToResponse(found));
  }


  @GetMapping
  public List<CategoryResponse> getAll() {
    return categoryService.getAllCategories().stream()
        .map(categoryMapper::mapToResponse)
        .toList();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    categoryService.softDeleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}
