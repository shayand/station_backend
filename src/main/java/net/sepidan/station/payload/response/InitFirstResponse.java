package net.sepidan.station.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InitFirstResponse {
  String version;


  String deviceId;
}
