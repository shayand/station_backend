package net.sepidan.station.persistent.repository;

import java.util.List;
import java.util.Optional;
import net.sepidan.station.persistent.domain.UserCallLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCallLogRepository extends ElasticsearchRepository<UserCallLog, String> {

  List<UserCallLog> findAllByDeletedAtIsNull();

  Optional<UserCallLog> findByIdAndDeletedAtIsNull(String id);
}
