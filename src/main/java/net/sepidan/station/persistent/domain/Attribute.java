package net.sepidan.station.persistent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.sepidan.station.persistent.domain.callback.audit.Auditable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "Attribute_index")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attribute implements Serializable, Auditable {


  @Id
  private String id; //  in postgres should be bigint

  @Field(name = "fa_name", type = FieldType.Text)
  private String faName;

  @Field(name = "en_name", type = FieldType.Text)
  private String enName;

  @Field(type = FieldType.Nested)
  @NonNull
  private AttributeDataBaseType attributeType;

  @Field(type = FieldType.Text)
  private String description;

  @Field(name = "created_at", type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime createdAt;

  @Field(name = "updated_at",type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime updatedAt;

  @Field(name = "deleted_at",type = FieldType.Date, format = DateFormat.date_hour_minute_second)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private ZonedDateTime deletedAt; // will fill with its call back class


  /*
    -- auto-generated definition
    create table attribute
    (
        id                bigint not null
            primary key,
        fa_name           varchar(255),
        en_name           varchar(256),
        description       text,
        created_at        timestamp,
        updated_at        timestamp,
        deleted_at        timestamp,
        attribute_type_id bigint
            constraint fk_attribute_type
                references attribute_type
    );

    alter table attribute
        owner to postgres;
*/
}
