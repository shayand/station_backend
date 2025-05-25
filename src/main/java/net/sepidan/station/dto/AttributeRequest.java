package net.sepidan.station.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttributeRequest {

  private String faName;

  private String enName;

  private AttributeDataBaseType attributeType;

  private String description;

}
