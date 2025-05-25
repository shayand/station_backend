package net.sepidan.station.dto;


import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class CategoryResponse {

  private String id;
  private String categoryName;
  private String description;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}