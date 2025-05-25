package net.sepidan.station.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.sepidan.station.enums.DatabaseType;

@Data
public class AttributeDataBaseTypeRequest {

  private String id; // Optional for update, can be omitted for create
  @NotNull
  private DatabaseType databaseType;
}
