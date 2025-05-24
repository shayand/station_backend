package net.sepidan.station.persistent.domain.seed;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.enums.DatabaseType;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;
import net.sepidan.station.persistent.service.AttributeDataBaseTypeService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AttributeTypeSeed {

  private final AttributeDataBaseTypeService attributeTypeService;

  @PostConstruct
  public void seed() {
    // Avoid duplicate seeding if already present
    if (attributeTypeService.count() > 0) {
      return;
    }

    for (DatabaseType dbType : DatabaseType.values()) {
      AttributeDataBaseType attributeDataBaseType = new AttributeDataBaseType();
      // For id, you can set null or generate as needed; Elasticsearch can auto-generate id
      attributeDataBaseType.setId(null);
      attributeDataBaseType.setDatabaseType(dbType);

      attributeTypeService.save(attributeDataBaseType);
      log.info("AttributeDataBaseTypeRepository " + attributeDataBaseType.getDatabaseType().name()
          + " saved");
    }

  }
}
