package com.emrekayacik.weather.mapper;

import com.emrekayacik.weather.base.mapper.BaseMapper;
import com.emrekayacik.weather.dto.UserWeatherDto;
import com.emrekayacik.weather.entity.UserWeather;
import com.emrekayacik.weather.response.UserSaveCityByNameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for mapping UserWeather entities and DTOs.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserWeatherMapper extends BaseMapper<UserWeather, UserWeatherDto> {

    /**
     * Converts a UserWeather entity to a UserSaveCityByNameResponse object.
     *
     * @param entity the UserWeather entity
     * @return the converted UserSaveCityByNameResponse object
     */
    @Mapping(target="username", source="entity.user.username")
    UserSaveCityByNameResponse convertEntityToResponse(UserWeather entity);

}
