package net.sepidan.station.persistent.domain.callback;

import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import net.sepidan.station.persistent.domain.Attribute;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttributeCallback implements BeforeConvertCallback<Attribute> {

  /*
  When you call save(entity), before Spring Data converts your entity into a JSON document, it calls this onBeforeConvert method.
   */

  private final ElasticsearchOperations esOps;

  @Override
  public Attribute onBeforeConvert(Attribute entity, IndexCoordinates index) {
    ZonedDateTime now = ZonedDateTime.now();
    if (entity.getCreatedAt() == null) {
      entity.setCreatedAt(now);
    }
    entity.setUpdatedAt(now);
    return entity;
  }

  public void softDelete(String id) {
    Attribute existing = esOps.get(id, Attribute.class, IndexCoordinates.of("user_call_logs"));
    if (existing != null) {
      existing.setDeletedAt(ZonedDateTime.now());
      esOps.save(existing);  // triggers the BeforeConvertCallback too
    }
  }
}

