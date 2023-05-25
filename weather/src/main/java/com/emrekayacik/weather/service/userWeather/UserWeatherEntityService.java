package com.emrekayacik.weather.service.userWeather;

import com.emrekayacik.weather.base.service.BaseEntityService;
import com.emrekayacik.weather.entity.UserWeather;
import com.emrekayacik.weather.repository.UserWeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class UserWeatherEntityService extends BaseEntityService<UserWeather, UserWeatherRepository> {

    public UserWeatherEntityService(UserWeatherRepository repository) {
        super(repository);
    }
}
