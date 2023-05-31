package com.emrekayacik.weather.repository;

import com.emrekayacik.weather.entity.UserWeather;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for managing UserWeather entities.
 */
public interface UserWeatherRepository extends JpaRepository<UserWeather, Long> {

}
