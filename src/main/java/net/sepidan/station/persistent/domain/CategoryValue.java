package net.sepidan.station.persistent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sepidan.station.persistent.domain.callback.audit.Auditable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@Document(indexName = "category_values_index")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryValue implements Serializable, Auditable {

  @Id
  private String id; //  in postgres should be bigint

  @Field(type = FieldType.Long)
  private Long categoryId; // Reference to Category entity

  @Field(type = FieldType.Text)
  private String value;

  @Field(type = FieldType.Text)
  private String label;

  @Field(type = FieldType.Integer)
  private Integer sortOrder;

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
create table category_values
(
    id          bigint generated always as identity
        primary key,
    category_id bigint
        references categories
            on delete cascade,
    value       varchar(255) not null,
    label       varchar(255) not null,
    sort_order  integer      not null,
    created_at  timestamp,
    updated_at  timestamp,
    deleted_at  timestamp
);

alter table category_values
    owner to postgres;
   */
}

