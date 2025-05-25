package net.sepidan.station.dto;


import lombok.Data;

@Data
public class CategoryValueRequest {

  private Long categoryId;
  private String value;
  private String label;
  private Integer sortOrder;
}