package net.sepidan.station.persistent.repository;

import net.sepidan.station.persistent.domain.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

}
