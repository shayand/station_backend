package net.sepidan.station.dto;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttributeResponceDto {

  private String id; //  in postgres should be bigint

  private String faName;

  private String enName;

  private AttributeDataBaseType attributeType;

  private String description;

  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}
