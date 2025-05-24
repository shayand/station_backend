package net.sepidan.station.persistent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;
import net.sepidan.station.enums.CallOutcome;
import net.sepidan.station.persistent.domain.callback.audit.Auditable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "user_call_logs")
@Data
public class UserCallLog implements Serializable, Auditable {

  @Id
  private String id; //  in postgres should be bigint

  @NotNull
  private UUID userId;

  @Field(name = "employee_id", type = FieldType.Nested)
  @NotNull
  private Employee employee;

  @NotNull
  private String phone;
  @NotNull
  private Integer callDurationSeconds;

  private String message;

  @NotNull
  private CallOutcome callOutcome;

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
  create table user_call_logs
(
    id                    bigint generated always as identity
        primary key,
    user_id               uuid                    not null,
    employee_id           uuid                    not null,
    phone                 varchar(20)             not null,
    call_duration_seconds integer   default 0     not null,
    message               text                    not null,
    call_outcome          varchar(50)             not null,
    created_at            timestamp default now() not null,
    updated_at            timestamp default now() not null,
    deleted_at            timestamp default now() not null
);
 */
}
