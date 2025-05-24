package net.sepidan.station.persistent.domain.callback.audit;

import java.time.ZonedDateTime;

/**
 * Any document that has createdAt/updatedAt
 */
public interface Auditable {

  ZonedDateTime getCreatedAt();

  ZonedDateTime getUpdatedAt();

  void setCreatedAt(ZonedDateTime ts);

  void setUpdatedAt(ZonedDateTime ts);

  void setDeletedAt(ZonedDateTime now);
}
