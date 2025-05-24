package net.sepidan.station.persistent.repository;

import net.sepidan.station.persistent.domain.CategoryValue;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryValueRepository extends ElasticsearchRepository<CategoryValue, String> {

}
