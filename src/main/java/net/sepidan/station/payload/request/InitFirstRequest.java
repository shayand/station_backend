package net.sepidan.station.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.sepidan.station.enums.AppMarket;


@AllArgsConstructor
@Data
public class InitFirstRequest {

  @NotEmpty()
  @Schema(description = "Version of the application", example = "1.0.0")
  String version;
  @NotEmpty()
  @Schema(description = "Market type", example = "GOOGLE")
  AppMarket market;

  String deviceId;

}
