package com.emrekayacik.weather.entity;

import com.emrekayacik.weather.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class that represents which user saved which city in the application.
 */

@Entity
@Table(name = "WEATHER_SAVED",uniqueConstraints = { @UniqueConstraint(name = "UQ_USER_CITY_NAME", columnNames = { "CITY_NAME", "USER_ID" }) })
@Getter
@Setter
public class UserWeather extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weatherSavedSqGenerator")
    @SequenceGenerator(name = "weatherSavedSqGenerator", sequenceName = "SQ_WEATHER_SAVED",allocationSize = 1)
    private Long id;

    @Column(name = "CITY_NAME",length = 100 , nullable = false)
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;
}
