package net.sepidan.station.persistent.service;

import java.util.List;
import net.sepidan.station.enums.DatabaseType;
import net.sepidan.station.persistent.domain.AttributeDataBaseType;

public interface AttributeDataBaseTypeService {


  public List<AttributeDataBaseType> getAll();

  public AttributeDataBaseType findById(String id);

  public AttributeDataBaseType save(AttributeDataBaseType attributeDataBaseType);

  public Long count();


  public AttributeDataBaseType create(AttributeDataBaseType attributeDataBaseType);

  public AttributeDataBaseType update(String id, DatabaseType newType);
}
