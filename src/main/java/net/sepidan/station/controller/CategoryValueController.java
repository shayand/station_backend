package net.sepidan.station.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.dto.CategoryValueRequest;
import net.sepidan.station.dto.CategoryValueResponse;
import net.sepidan.station.mapper.CategoryValueMapper;
import net.sepidan.station.persistent.domain.CategoryValue;
import net.sepidan.station.persistent.service.CategoryValueService;
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
@RequestMapping("/api/category-values")
@RequiredArgsConstructor
public class CategoryValueController {

  private final CategoryValueService categoryValueService;
  private final CategoryValueMapper categoryValueMapper;

  @GetMapping
  public List<CategoryValueResponse> getAll() {
    return categoryValueService.getAllActiveCategoryValues().stream()
        .map(categoryValueMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryValueResponse> getById(@PathVariable String id) {
    CategoryValue found = categoryValueService.getCategoryValueById(id);
    return ResponseEntity.ok(categoryValueMapper.mapToResponse(found));
  }

  @PostMapping
  public ResponseEntity<CategoryValueResponse> create(@RequestBody CategoryValueRequest request) {
    CategoryValue saved = categoryValueService.createCategoryValue(
        categoryValueMapper.mapToDomain(request));
    return ResponseEntity.ok(categoryValueMapper.mapToResponse(saved));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryValueResponse> update(@PathVariable String id,
      @RequestBody CategoryValueRequest request) {
    CategoryValue categoryValue = categoryValueMapper.mapToDomain(request);
    var updated = categoryValueService.updateCategoryValue(id, categoryValue);
    return ResponseEntity.ok(categoryValueMapper.mapToResponse(updated));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    categoryValueService.SoftDelete(id);
    return ResponseEntity.noContent().build();
  }
}

