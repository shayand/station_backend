package net.sepidan.station.dto;

import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class CategoryValueResponse {

  private String id;
  private Long categoryId;
  private String value;
  private String label;
  private Integer sortOrder;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}