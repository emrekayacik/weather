package com.emrekayacik.weather.entity;

import com.emrekayacik.weather.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "WEATHER_SAVED")
@Getter
@Setter
public class UserWeatherSaved extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weatherSavedSqGenerator")
    @SequenceGenerator(name = "weatherSavedSqGenerator", sequenceName = "SQ_WEATHER_SAVED",allocationSize = 1)
    private Long id;

    @Column(name = "NAME",length = 100 , nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
