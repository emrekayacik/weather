package com.emrekayacik.weather.base.mapper;

import com.emrekayacik.weather.base.dto.BaseAuditableDto;
import com.emrekayacik.weather.base.entity.BaseAuditableEntity;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between BaseAuditableEntity and BaseAuditableDto.
 */
@Mapper
public interface AuditableMapper {

    /**
     * Converts a BaseAuditableEntity object to a BaseAuditableDto object.
     *
     * @param entity the BaseAuditableEntity object to be converted
     * @return the corresponding BaseAuditableDto object
     */
    BaseAuditableDto BaseAuditableEntityToBaseAuditableDto(BaseAuditableEntity entity);

    /**
     * Converts a BaseAuditableDto object to a BaseAuditableEntity object.
     *
     * @param dto the BaseAuditableDto object to be converted
     * @return the corresponding BaseAuditableEntity object
     */
    BaseAuditableEntity BaseAuditableDtoToBaseAuditableEntity(BaseAuditableDto dto);
}
