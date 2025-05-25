package net.sepidan.station.persistent.service;

import java.util.List;
import net.sepidan.station.persistent.domain.UserCallLogAttribute;

public interface UserCallLogAttributeService {
  // Create
  public UserCallLogAttribute create(UserCallLogAttribute entity);

  // Read all (non-deleted)
  public List<UserCallLogAttribute> getAllUserCallLogAttribute();

  // Read one (non-deleted)
  public UserCallLogAttribute getSingleById(String id);

  // Update (non-deleted only)
  public UserCallLogAttribute update(String id, UserCallLogAttribute updated);

  // Soft Delete
  public void softDelete(String id);
}
