package net.sepidan.station.persistent.domain.callback;

import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.persistent.domain.UserCallLog;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCallLogCallback implements BeforeConvertCallback<UserCallLog> {

  /*
  When you call save(userCallLog), before Spring Data converts your UserCallLog into a JSON document, it calls this onBeforeConvert method.
   */

  private final ElasticsearchOperations esOps;

  @Override
  public UserCallLog onBeforeConvert(UserCallLog entity, IndexCoordinates index) {
    ZonedDateTime now = ZonedDateTime.now();
    if (entity.getCreatedAt() == null) {
      entity.setCreatedAt(now);
    }
    entity.setUpdatedAt(now);
    return entity;
  }

  public void softDelete(String id) {
    UserCallLog existing = esOps.get(id, UserCallLog.class, IndexCoordinates.of("user_call_logs"));
    if (existing != null) {
      existing.setDeletedAt(ZonedDateTime.now());
      esOps.save(existing);  // triggers the BeforeConvertCallback too
    }
  }
}
