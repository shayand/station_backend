package net.sepidan.station.mapper;

/**
 * Base interface for mapping between DTOs and Domain models.
 *
 * @param <DTO> the Data Transfer Object (request)
 * @param <DOMAIN> the domain model (entity)
 * @param <RESPONCE> the response from entity (entity)
 */
public interface BaseDtoDomainMapper<DTO, DOMAIN> {

  DTO mapToDto(DOMAIN domain);

  DOMAIN mapToDomain(DTO dto);


}

