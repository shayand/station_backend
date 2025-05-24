package net.sepidan.station.persistent.repository;

import java.util.List;
import net.sepidan.station.persistent.domain.UserCallLogAttribute;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCallLogAttributeRepository extends
    ElasticsearchRepository<UserCallLogAttribute, String> {

  List<UserCallLogAttribute> findAllByDeletedAtIsNotNull();

}
