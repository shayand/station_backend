package net.sepidan.station.persistent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Data;
import net.sepidan.station.persistent.domain.callback.audit.Auditable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "category_index")
public class Category implements Serializable, Auditable {

  @Id
  private String id;

  @Field(type = FieldType.Text)
  private String categoryName;

  @Field(type = FieldType.Text)
  private String description;

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime createdAt;

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime updatedAt;

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime deletedAt;


  /*
-- auto-generated definition
create table categories
(
    id            bigint generated always as identity
        primary key,
    category_name varchar(255) not null,
    description   text,
    deleted_at    timestamp,
    created_at    timestamp,
    updated_at    timestamp
);

alter table categories
    owner to postgres;
   */

}
