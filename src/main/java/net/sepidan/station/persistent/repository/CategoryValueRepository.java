package net.sepidan.station.persistent.repository;

import java.util.List;
import net.sepidan.station.persistent.domain.CategoryValue;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryValueRepository extends ElasticsearchRepository<CategoryValue, String> {

  List<CategoryValue> findAllByDeletedAtIsNull();

  // Find soft-deleted entries
  List<CategoryValue> findAllByDeletedAtIsNotNull();


}
