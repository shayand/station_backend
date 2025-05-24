package net.sepidan.station.persistent.repository;

import net.sepidan.station.persistent.domain.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ElasticsearchRepository<Category, String> {

}
