package com.emrekayacik.weather.base.mapper;

import com.emrekayacik.weather.base.dto.BaseDto;
import com.emrekayacik.weather.base.entity.BaseEntity;
import jakarta.persistence.MappedSuperclass;


import java.util.List;

@MappedSuperclass
public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

    E convertToEntity(D productDto);

    D convertToDto(E product);

    List<D> convertToDtoList(List<E> entityList);
    List<E> convertToEntityList(List<D> dtoList);
}
