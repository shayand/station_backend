package net.sepidan.station.persistent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.Data;
import net.sepidan.station.persistent.domain.callback.audit.Auditable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user_call_logs_attribute")
@Data
public class UserCallLogAttribute implements Serializable, Auditable {

  @Id
  private String id; //  in postgres should be bigint

  @Field(type = FieldType.Nested)
  @NotNull
  private UserCallLog userCallLog;

  @Field(name = "attribute_id", type = FieldType.Nested)
  @NotNull
  private Attribute attribute;

  @Field(name = "category_id", type = FieldType.Nested)
  private Category category;

  @Field(name = "category_value_id", type = FieldType.Nested)
  private CategoryValue categoryValue;

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime createdAt; // will fill with its call back class

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime updatedAt; // will fill with its call back class

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime deletedAt; // will fill with its call back class

  @Field(type = FieldType.Text)
  private String valueText;

  @Field(type = FieldType.Integer)
  private Integer valueInteger;

  @Field(type = FieldType.Double)
  private BigDecimal valueDecimal;

  @Field(type = FieldType.Boolean)
  private Boolean valueBoolean;

  @Field(type = FieldType.Date, format = DateFormat.basic_date)  // yyyyMMdd
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate valueDate;

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime valueTimestamp;

  @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
  private ZonedDateTime valueTimestamptz;


  /*

Data Source: crm_db@localhost
Database: crm_db
Schema: public
Table: user_call_log_attributes


-- auto-generated definition
create table user_call_log_attributes
(
    id                bigint                  not null
        primary key,
    user_call_log_id  bigint                  not null,
    attribute_id      bigint
        references attribute
            on delete cascade,
    category_id       bigint
        references categories
            on delete cascade,
    category_value_id bigint
        references category_values
            on delete cascade,
    created_at        timestamp default now() not null,
    updated_at        timestamp default now() not null,
    value_text        text,
    value_integer     integer,
    value_decimal     numeric,
    value_boolean     boolean,
    value_date        date,
    value_timestamp   timestamp,
    value_timestamptz timestamp with time zone
);

alter table user_call_log_attributes
    owner to postgres;
   */
}
