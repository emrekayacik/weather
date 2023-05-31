package com.emrekayacik.weather.service.userWeather;

import com.emrekayacik.weather.base.service.BaseEntityService;
import com.emrekayacik.weather.entity.UserWeather;
import com.emrekayacik.weather.repository.UserWeatherRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for managing UserWeather entities, extending the BaseEntityService.
 */
@Service
public class UserWeatherEntityService extends BaseEntityService<UserWeather, UserWeatherRepository> {
    /**
     * Constructs a new UserWeatherEntityService with the provided repository.
     *
     * @param repository the UserWeatherRepository to be used for database operations
     */
    public UserWeatherEntityService(UserWeatherRepository repository) {
        super(repository);
    }


}
