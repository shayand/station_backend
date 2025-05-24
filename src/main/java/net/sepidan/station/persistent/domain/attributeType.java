package net.sepidan.station.persistent.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.sepidan.station.enums.DatabaseType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "attribute_type")
@Data
public class attributeType {


  @Id
  private String id; //  in postgres should be bigint

  @NotNull
  private DatabaseType databaseType;

}
