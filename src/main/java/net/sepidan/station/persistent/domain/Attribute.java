package net.sepidan.station.persistent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Data;
import lombok.NonNull;
import net.sepidan.station.persistent.domain.callback.audit.Auditable;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.AttributeType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "Attribute")
@Data
public class Attribute implements Serializable, Auditable {


  @Id
  private String id; //  in postgres should be bigint

  @Field(type = FieldType.Text)
  private String faName;

  @Field(type = FieldType.Text)
  private String enName;

  @Field(type = FieldType.Nested)
  @NonNull
  private AttributeType attributeType;

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
  private ZonedDateTime deletedAt; // will fill with its call back class


  /*
Data Source: crm_db@localhost
Database: crm_db
Schema: public
Table: attribute


-- auto-generated definition
create table attribute
(
    id             bigint                  not null
        primary key,
    fa_name        varchar(255),
    en_name        varchar(256),
    attribute_type varchar(50),
    description    text,
    created_at     timestamp default now() not null,
    updated_at     timestamp default now() not null
);

alter table attribute
    owner to postgres;
*/
}
