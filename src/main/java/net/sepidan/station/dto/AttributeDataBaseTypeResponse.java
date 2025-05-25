package net.sepidan.station.dto;

import lombok.Data;
import net.sepidan.station.enums.DatabaseType;

@Data
public class AttributeDataBaseTypeResponse {

  private String id;
  private DatabaseType databaseType;
}
