package net.sepidan.station.controller;

import java.util.List;
import java.util.stream.Collectors;
import net.sepidan.station.dto.AttributeDataBaseTypeRequest;
import net.sepidan.station.dto.AttributeDataBaseTypeResponse;
import net.sepidan.station.mapper.AttributeDataBaseTypeMapper;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;
import net.sepidan.station.persistent.service.AttributeDataBaseTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attribute-database-types")
public class AttributeDataBaseTypeController {

  private final AttributeDataBaseTypeService attributeDataBaseTypeService;
  private final AttributeDataBaseTypeMapper attributeDataBaseTypeMapper;

  public AttributeDataBaseTypeController(AttributeDataBaseTypeService service,
      AttributeDataBaseTypeMapper mapper) {
    this.attributeDataBaseTypeService = service;
    this.attributeDataBaseTypeMapper = mapper;
  }

  @PostMapping
  public ResponseEntity<AttributeDataBaseTypeResponse> create(
      @RequestBody AttributeDataBaseTypeRequest request) {
    var domain = attributeDataBaseTypeMapper.mapToDomain(request);
    var saved = attributeDataBaseTypeService.save(domain);
    return ResponseEntity.ok(attributeDataBaseTypeMapper.mapToResponse(saved));
  }

  @GetMapping
  public ResponseEntity<List<AttributeDataBaseTypeResponse>> getAll() {
    var list = attributeDataBaseTypeService.getAll();
    var responses = list.stream().map(attributeDataBaseTypeMapper::mapToResponse)
        .collect(Collectors.toList());
    return ResponseEntity.ok(responses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AttributeDataBaseTypeResponse> getById(@PathVariable String id) {
    AttributeDataBaseType found = attributeDataBaseTypeService.findById(id);
    if (found == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(attributeDataBaseTypeMapper.mapToResponse(found));
  }


  @PutMapping("/{id}")
  public ResponseEntity<AttributeDataBaseTypeResponse> update(@PathVariable String id,
      @RequestBody AttributeDataBaseTypeRequest request) {
    AttributeDataBaseType domain = attributeDataBaseTypeMapper.mapToDomain(request);
    AttributeDataBaseType updated = attributeDataBaseTypeService.update(id, domain.getDatabaseType());
    return ResponseEntity.ok(attributeDataBaseTypeMapper.mapToResponse(updated));
  }

}
