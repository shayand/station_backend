package net.sepidan.station.persistent.repository;

import java.util.List;
import net.sepidan.station.persistent.domain.Attribute;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends ElasticsearchRepository<Attribute, String> {

  List<Attribute> findAllByDeletedAtIsNull();

}
