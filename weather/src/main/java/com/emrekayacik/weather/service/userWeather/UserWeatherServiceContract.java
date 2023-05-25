package com.emrekayacik.weather.service.userWeather;

import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.dto.UserWeatherDto;
import com.emrekayacik.weather.entity.UserWeather;
import com.emrekayacik.weather.mapper.UserMapper;
import com.emrekayacik.weather.mapper.UserWeatherMapper;
import com.emrekayacik.weather.request.UserSaveCityByNameRequest;
import com.emrekayacik.weather.response.UserSaveCityByNameResponse;
import com.emrekayacik.weather.service.user.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWeatherServiceContract {
    private final UserWeatherEntityService userWeatherEntityService;
    private final UserEntityService userEntityService;
    UserWeatherMapper INSTANCE = Mappers.getMapper(UserWeatherMapper.class);
    UserMapper INSTANCE_USER_MAPPER = Mappers.getMapper(UserMapper.class);


    public UserSaveCityByNameResponse save(UserSaveCityByNameRequest request){
        UserDto userDto = INSTANCE_USER_MAPPER.convertToDto(userEntityService.findFirstByUsername(request.username()));

        UserWeatherDto userWeatherDto = new UserWeatherDto(null,request.city(),userDto);
        UserWeather userWeather = INSTANCE.convertToEntity(userWeatherDto);
        userWeather = userWeatherEntityService.save(userWeather);

        return INSTANCE.convertEntityToResponse(userWeather);
    }

}
