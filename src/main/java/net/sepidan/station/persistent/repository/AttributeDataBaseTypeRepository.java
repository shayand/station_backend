package net.sepidan.station.persistent.repository;

import net.sepidan.station.persistent.domain.AttributeDataBaseType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeDataBaseTypeRepository extends
    ElasticsearchRepository<AttributeDataBaseType, String> {

}
