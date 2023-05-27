package com.emrekayacik.weather.base.mapper;

import com.emrekayacik.weather.base.dto.BaseDto;
import com.emrekayacik.weather.base.entity.BaseEntity;
import jakarta.persistence.MappedSuperclass;

import java.util.List;

/**
 * Generic mapper interface for converting between BaseEntity and BaseDto objects.
 *
 * @param <E> the type of the BaseEntity
 * @param <D> the type of the BaseDto
 */
@MappedSuperclass
public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

    /**
     * Converts a BaseDto object to a BaseEntity object.
     *
     * @param dto the BaseDto object to be converted
     * @return the corresponding BaseEntity object
     */
    E convertToEntity(D dto);

    /**
     * Converts a BaseEntity object to a BaseDto object.
     *
     * @param entity the BaseEntity object to be converted
     * @return the corresponding BaseDto object
     */
    D convertToDto(E entity);

    /**
     * Converts a list of BaseEntity objects to a list of BaseDto objects.
     *
     * @param entityList the list of BaseEntity objects to be converted
     * @return the corresponding list of BaseDto objects
     */
    List<D> convertToDtoList(List<E> entityList);

    /**
     * Converts a list of BaseDto objects to a list of BaseEntity objects.
     *
     * @param dtoList the list of BaseDto objects to be converted
     * @return the corresponding list of BaseEntity objects
     */
    List<E> convertToEntityList(List<D> dtoList);
}
