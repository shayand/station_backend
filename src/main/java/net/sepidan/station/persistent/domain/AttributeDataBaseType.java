package net.sepidan.station.persistent.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.sepidan.station.enums.DatabaseType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "attribute_type_index")
@Data
public class AttributeDataBaseType {


  @Id
  private String id; //  in postgres should be bigint

  @NotNull
  private DatabaseType databaseType;

  // todo: use seed for this
/*

  -- auto-generated definition
  create table attribute_type
  (
      id            bigint       not null
          primary key,
      database_type varchar(255) not null
  );

  alter table attribute_type
      owner to postgres;

  INSERT INTO attribute_type (id, database_type) VALUES (1, 'TEXT');
  INSERT INTO attribute_type (id, database_type) VALUES (2, 'VARCHAR');
  INSERT INTO attribute_type (id, database_type) VALUES (3, 'INTEGER');
  INSERT INTO attribute_type (id, database_type) VALUES (4, 'BIGINT');
  INSERT INTO attribute_type (id, database_type) VALUES (5, 'BOOLEAN');
  INSERT INTO attribute_type (id, database_type) VALUES (6, 'NUMERIC');
  INSERT INTO attribute_type (id, database_type) VALUES (7, 'DATE');
  INSERT INTO attribute_type (id, database_type) VALUES (8, 'TIMESTAMP WITHOUT TIME ZONE');
  INSERT INTO attribute_type (id, database_type) VALUES (9, 'TIMESTAMP WITH TIME ZONE');
  */


}
