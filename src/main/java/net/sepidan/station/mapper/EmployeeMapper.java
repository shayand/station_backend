package net.sepidan.station.mapper;

import java.time.ZonedDateTime;
import java.util.UUID;
import net.sepidan.station.dto.EmployeeRequest;
import net.sepidan.station.dto.EmployeeResponse;
import net.sepidan.station.persistent.domain.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements BaseDtoDomainMapper<EmployeeRequest, Employee> {

  @Override
  public EmployeeRequest mapToDto(Employee employee) {
    if (employee == null) {
      return null;
    }

    EmployeeRequest dto = new EmployeeRequest();
    dto.setFirstName(employee.getFirstName());
    dto.setLastName(employee.getLastName());
    dto.setPhone(employee.getPhone());
    return dto;
  }

  @Override
  public Employee mapToDomain(EmployeeRequest request) {
    if (request == null) {
      return null;
    }

    Employee employee = new Employee();
    employee.setId(UUID.randomUUID().toString());
    employee.setFirstName(request.getFirstName());
    employee.setLastName(request.getLastName());
    employee.setPhone(request.getPhone());
    employee.setCreatedAt(ZonedDateTime.now());
    return employee;
  }

  public EmployeeResponse mapToResponse(Employee employee) {
    if (employee == null) {
      return null;
    }

    EmployeeResponse response = new EmployeeResponse();
    response.setId(employee.getId());
    response.setFirstName(employee.getFirstName());
    response.setLastName(employee.getLastName());
    response.setPhone(employee.getPhone());
    response.setCreatedAt(employee.getCreatedAt());
    response.setUpdatedAt(employee.getUpdatedAt());
    return response;
  }
}

