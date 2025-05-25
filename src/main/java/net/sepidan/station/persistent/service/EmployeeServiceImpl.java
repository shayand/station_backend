package net.sepidan.station.persistent.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.persistent.domain.Employee;
import net.sepidan.station.persistent.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

  private final EmployeeRepository employeeRepository;

  // Create
  public Employee createEmployee(Employee employee) {
    log.info("Creating new employee");
    return employeeRepository.save(employee);
  }

  // Read all (non-deleted)
  public List<Employee> getAllActiveEmployees() {
    return employeeRepository.findAllByDeletedAtIsNull();
  }

  // Read one (non-deleted)
  public Employee getEmployeeById(String id) {
    return employeeRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(
            () -> new NoSuchElementException("Employee not found or deleted with ID: " + id));
  }

  // Update
  public Employee updateEmployee(String id, Employee updatedEmployee) {
    return employeeRepository.findByIdAndDeletedAtIsNull(id)
        .map(existing -> {
          existing.setFirstName(updatedEmployee.getFirstName());
          existing.setLastName(updatedEmployee.getLastName());
          existing.setPhone(updatedEmployee.getPhone());
          return employeeRepository.save(existing);
        })
        .orElseThrow(
            () -> new NoSuchElementException("Employee not found or deleted with ID: " + id));
  }

  // Soft Delete
  public void softDelete(String id) {
    employeeRepository.findByIdAndDeletedAtIsNull(id)
        .ifPresent(existing -> {
          existing.setDeletedAt(ZonedDateTime.now());
          employeeRepository.save(existing);
          log.info("Soft deleted employee with ID: {}", id);
        });
  }

}
