package net.sepidan.station.persistent.service;

import java.util.List;
import net.sepidan.station.persistent.domain.Attribute;

public interface AttributeService {

  // Create
  public Attribute save(Attribute attribute);

  // Read (by ID)
  public Attribute getAttributeById(String id);

  // Read (all)
  public List<Attribute> getAllAttributes();

  // Update
  public Attribute updateAttribute(String id, Attribute updatedAttribute);

  // Delete (soft or hard)
  public void softDelete(String id);
}
