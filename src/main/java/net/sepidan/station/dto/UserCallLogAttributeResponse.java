package net.sepidan.station.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class UserCallLogAttributeResponse {

  private String id;
  private String userCallLogId;
  private String attributeId;
  private String categoryId;
  private String categoryValueId;

  private String valueText;
  private Integer valueInteger;
  private BigDecimal valueDecimal;
  private Boolean valueBoolean;
  private LocalDate valueDate;
  private LocalDateTime valueTimestamp;
  private ZonedDateTime valueTimestamptz;

  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}
