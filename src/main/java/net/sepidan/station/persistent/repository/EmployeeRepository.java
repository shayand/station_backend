package net.sepidan.station.persistent.repository;

import java.util.List;
import net.sepidan.station.persistent.domain.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

  List<Employee> findAllByDeletedAtIsNotNull();

}
