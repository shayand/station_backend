package net.sepidan.station.controller;


import java.util.List;
import java.util.stream.Collectors;
import net.sepidan.station.dto.AttributeRequest;
import net.sepidan.station.dto.AttributeResponse;
import net.sepidan.station.dto.CategoryValueResponse;
import net.sepidan.station.mapper.AttributeMapper;
import net.sepidan.station.persistent.domain.Attribute;
import net.sepidan.station.persistent.domain.CategoryValue;
import net.sepidan.station.persistent.service.AttributeService;
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
@RequestMapping("/api/attributes")
public class AttributeController {

  private final AttributeService attributeService;
  private final AttributeMapper attributeMapper;

  public AttributeController(AttributeService attributeService, AttributeMapper attributeMapper) {
    this.attributeService = attributeService;
    this.attributeMapper = attributeMapper;
  }

  // Create
  @PostMapping
  public ResponseEntity<AttributeResponse> createAttribute(@RequestBody AttributeRequest request) {
    Attribute attribute = attributeMapper.mapToDomain(request);
    Attribute saved = attributeService.save(attribute);
    return ResponseEntity.ok(attributeMapper.mapToResponse(saved));
  }

  // Read all
  @GetMapping
  public ResponseEntity<List<AttributeResponse>> getAllAttributes() {
    List<Attribute> attributes = attributeService.getAllAttributes();
    List<AttributeResponse> responses = attributes.stream()
        .map(attributeMapper::mapToResponse)
        .toList();
    return ResponseEntity.ok(responses);
  }

  // Read one by id
  @GetMapping("/{id}")
  public ResponseEntity<AttributeResponse> getAttributeById(@PathVariable String id) {
    Attribute found = attributeService.getAttributeById(id);
    return ResponseEntity.ok(attributeMapper.mapToResponse(found));
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<AttributeResponse> updateAttribute(@PathVariable String id,
      @RequestBody AttributeRequest request) {
    Attribute updated = attributeService.updateAttribute(id, attributeMapper.mapToDomain(request));
    return ResponseEntity.ok(attributeMapper.mapToResponse(updated));
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAttribute(@PathVariable String id) {
    attributeService.softDelete(id);
    return ResponseEntity.noContent().build();
  }
}

