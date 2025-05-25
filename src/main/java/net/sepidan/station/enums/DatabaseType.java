package net.sepidan.station.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DatabaseType {

  TEXT("TEXT"),
  VARCHAR("VARCHAR"),
  INTEGER("INTEGER"),
  BIGINT("BIGINT"),
  BOOLEAN("BOOLEAN"),
  NUMERIC("NUMERIC"),
  DATE("DATE"),
  TIMESTAMP("TIMESTAMP WITHOUT TIME ZONE"),
  TIMESTAMPTZ("TIMESTAMP WITH TIME ZONE");

  private final String sql;

}

