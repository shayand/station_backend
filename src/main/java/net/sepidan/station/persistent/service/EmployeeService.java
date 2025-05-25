package net.sepidan.station.persistent.service;

import java.util.List;
import net.sepidan.station.persistent.domain.Employee;

public interface EmployeeService {

  // Create
  public Employee createEmployee(Employee employee);

  // Read all (non-deleted)
  public List<Employee> getAllActiveEmployees();

  // Read one (non-deleted)
  public Employee getEmployeeById(String id);


  // Update
  public Employee updateEmployee(String id, Employee updatedEmployee);

  // Soft Delete
  public void softDelete(String id);
}
