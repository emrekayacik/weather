package com.emrekayacik.weather.repository;

import com.emrekayacik.weather.entity.UserWeatherSaved;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWeatherSavedRepository extends JpaRepository<UserWeatherSaved, Long> {

}
