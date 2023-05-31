package com.emrekayacik.weather.service.userWeather;

import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.dto.UserWeatherDto;
import com.emrekayacik.weather.entity.UserWeather;
import com.emrekayacik.weather.mapper.UserMapper;
import com.emrekayacik.weather.mapper.UserWeatherMapper;
import com.emrekayacik.weather.request.UserSaveCityByNameRequest;
import com.emrekayacik.weather.response.UserSaveCityByNameResponse;
import com.emrekayacik.weather.service.user.UserEntityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Contract class for managing UserWeather operations based on city names.
 * This class provides mappings and conversions between DTOs, requests, responses and entities
 * and uses the UserWeatherEntityService for database operations.
 */
@Service
@RequiredArgsConstructor
public class UserWeatherServiceContract {
    private final UserWeatherEntityService userWeatherEntityService;
    private final UserEntityService userEntityService;
    UserWeatherMapper INSTANCE = Mappers.getMapper(UserWeatherMapper.class);
    UserMapper INSTANCE_USER_MAPPER = Mappers.getMapper(UserMapper.class);


    public List<UserWeatherDto> findAll(){
        List<UserWeather> userWeatherList = userWeatherEntityService.findAll();

        return INSTANCE.convertToDtoList(userWeatherList);
    }
    public UserWeatherDto findById(Long id){
        UserWeather userWeather = userWeatherEntityService.findById(id);
        return INSTANCE.convertToDto(userWeather);
    }

    public List<UserWeatherDto> findByUsername(String username){

        return findAll().stream()
                .filter(a->a.getUser().getUsername().equals(username))
                .toList();
    }
    public List<UserWeatherDto> findByCityName(String cityName){
        return findAll().stream()
                .filter(a->a.getCityName().equals(cityName))
                .toList();
    }

    public List<UserWeatherDto> findCurrentUserCities(){
        var currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(currentUserName);
    }

    @Transactional
    public UserSaveCityByNameResponse save(UserSaveCityByNameRequest request){
        UserDto userDto = INSTANCE_USER_MAPPER.convertToDto(userEntityService.findFirstByUsername(request.username()));

        UserWeatherDto userWeatherDto = new UserWeatherDto(null,request.city(),userDto);
        UserWeather userWeather = INSTANCE.convertToEntity(userWeatherDto);
        userWeather = userWeatherEntityService.save(userWeather);

        return INSTANCE.convertEntityToResponse(userWeather);
    }

    public void delete(Long id) {
        userWeatherEntityService.delete(INSTANCE.convertToEntity(findById(id)));
    }
}
