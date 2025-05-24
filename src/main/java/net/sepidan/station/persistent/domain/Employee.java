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


@Document(indexName = "employee_index")
@Data
public class Employee implements Serializable, Auditable {

  @Id
  private String id; //  in postgres should be bigint

  @Field(type = FieldType.Text)
  private String firstName;

  @Field(type = FieldType.Text)
  private String lastName;

  @Field(type = FieldType.Keyword)
  private String phone;

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime createdAt; // will fill with its call back class

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime updatedAt; // will fill with its call back class

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime deletedAt; // will fill with its call back class

  /*
  -- auto-generated definition
  create table employee
  (
      id         uuid default gen_random_uuid() not null
          primary key,
      first_name varchar(255),
      last_name  varchar(255),
      phone      varchar(20),
      created_at timestamp,
      updated_at timestamp,
      deleted_at timestamp
  );

  alter table employee
      owner to postgres;
   */

}
