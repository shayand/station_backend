package net.sepidan.station.persistent.repository;

import net.sepidan.station.persistent.domain.UserCallLogAttribute;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCallLogAttributeRepository extends
    ElasticsearchRepository<UserCallLogAttribute, String> {

}
