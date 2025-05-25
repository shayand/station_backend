package net.sepidan.station.persistent.domain.callback;


import java.time.ZonedDateTime;
import net.sepidan.station.persistent.domain.callback.audit.Auditable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;


/**
 * Base callback to set createdAt/updatedAt and provide a softDelete helper.
 */
public abstract class BaseAuditableCallback<T extends Auditable>
    implements BeforeConvertCallback<T> {

//  You do not need to manually set createdAt, updatedAt, or deletedAt in your service methods.
  private final ElasticsearchOperations esOps;

  protected BaseAuditableCallback(ElasticsearchOperations esOps) {
    this.esOps = esOps;
  }

  @Override
  public T onBeforeConvert(T entity, IndexCoordinates index) {
    ZonedDateTime now = ZonedDateTime.now();
    if (entity.getCreatedAt() == null) {
      entity.setCreatedAt(now);
    }
    entity.setUpdatedAt(now);
    return entity;
  }

  /**
   * Soft‐delete the document with the given id by setting deletedAt timestamp.
   */
  public void softDelete(String id) {
    T existing = esOps.get(id, getEntityClass(), getIndexCoordinates());
    if (existing != null) {
      existing.setDeletedAt(ZonedDateTime.now());
      esOps.save(existing);  // triggers the BeforeConvertCallback too
    }
  }

  /** Subclasses supply their entity class. */
  protected abstract Class<T> getEntityClass();

  /** Subclasses supply their index coordinates. */
  protected abstract IndexCoordinates getIndexCoordinates();
}