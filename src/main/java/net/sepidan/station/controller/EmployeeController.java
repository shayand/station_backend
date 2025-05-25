package net.sepidan.station.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.dto.EmployeeRequest;
import net.sepidan.station.dto.EmployeeResponse;
import net.sepidan.station.mapper.EmployeeMapper;
import net.sepidan.station.persistent.domain.Employee;
import net.sepidan.station.persistent.service.EmployeeService;
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
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper;

  @GetMapping
  public List<EmployeeResponse> getAll() {
    return employeeService.getAllActiveEmployees().stream()
        .map(employeeMapper::mapToResponse)
        .collect(Collectors.toList());
  }


  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponse> getById(@PathVariable String id) {
    Employee found = employeeService.getEmployeeById(id);
    return ResponseEntity.ok(employeeMapper.mapToResponse(found));
  }

  @PostMapping
  public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
    Employee saved = employeeService.createEmployee(employeeMapper.mapToDomain(request));
    return ResponseEntity.ok(employeeMapper.mapToResponse(saved));
  }


  @PutMapping("/{id}")
  public ResponseEntity<EmployeeResponse> update(@PathVariable String id,
      @RequestBody EmployeeRequest request) {
    Employee employee = employeeMapper.mapToDomain(request);
    var updated = employeeService.updateEmployee(id, employee);
    return ResponseEntity.ok(employeeMapper.mapToResponse(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    employeeService.softDelete(id);
    return ResponseEntity.noContent().build();
  }
}
