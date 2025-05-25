package net.sepidan.station.mapper;

import net.sepidan.station.dto.UserCallLogRequest;
import net.sepidan.station.dto.UserCallLogResponse;
import net.sepidan.station.persistent.domain.Employee;
import net.sepidan.station.persistent.domain.UserCallLog;
import org.springframework.stereotype.Component;

@Component
public class UserCallLogMapper implements BaseDtoDomainMapper<UserCallLogRequest, UserCallLog> {

  @Override
  public UserCallLogRequest mapToDto(UserCallLog entity) {
    if (entity == null) {
      return null;
    }

    UserCallLogRequest dto = new UserCallLogRequest();
    dto.setUserId(entity.getUserId());
    dto.setPhone(entity.getPhone());
    dto.setCallDurationSeconds(entity.getCallDurationSeconds());
    dto.setMessage(entity.getMessage());
    dto.setCallOutcome(entity.getCallOutcome());
    if (entity.getEmployee() != null) {
      dto.setEmployeeId(entity.getEmployee().getId());
    }
    return dto;
  }

  @Override
  public UserCallLog mapToDomain(UserCallLogRequest dto) {
    if (dto == null) {
      return null;
    }

    UserCallLog entity = new UserCallLog();
    entity.setUserId(dto.getUserId());
    entity.setPhone(dto.getPhone());
    entity.setCallDurationSeconds(dto.getCallDurationSeconds());
    entity.setMessage(dto.getMessage());
    entity.setCallOutcome(dto.getCallOutcome());

    if (dto.getEmployeeId() != null) {
      var employee = new Employee();
      employee.setId(dto.getEmployeeId());
      entity.setEmployee(employee);
    }

    return entity;
  }

  public UserCallLogResponse mapToResponse(UserCallLog entity) {
    if (entity == null) {
      return null;
    }

    UserCallLogResponse response = new UserCallLogResponse();
    response.setId(entity.getId());
    response.setUserId(entity.getUserId());
    response.setPhone(entity.getPhone());
    response.setCallDurationSeconds(entity.getCallDurationSeconds());
    response.setMessage(entity.getMessage());
    response.setCallOutcome(entity.getCallOutcome());
    response.setCreatedAt(entity.getCreatedAt());
    response.setUpdatedAt(entity.getUpdatedAt());

    if (entity.getEmployee() != null) {
      response.setEmployeeId(entity.getEmployee().getId());
      response.setEmployeeFullName(
          entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName());
    }

    return response;
  }
}

