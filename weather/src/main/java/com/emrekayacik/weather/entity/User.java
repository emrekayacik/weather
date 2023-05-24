package com.emrekayacik.weather.entity;

import com.emrekayacik.weather.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "USER_DEF")
@Getter
@Setter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSqGenerator")
    @SequenceGenerator(name = "userSqGenerator", sequenceName = "SQ_USER",allocationSize = 1)
    private Long id;
    @Column(name = "USERNAME",length = 50 , nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD",length = 100 , nullable = false)
    private String password;
    @Column(name = "NAME",length = 100 , nullable = false)
    private String name;
    @Column(name = "SURNAME",length = 100)
    private String surname;
    @Column(name = "EMAIL",nullable = false,length = 100,unique = true)
    private String email;
    @Column(name = "PHONE_NUMBER",nullable = false,length = 50,unique = true)
    private String phoneNumber;
    @OneToMany(mappedBy="user")
    private Set<UserWeatherSaved> savedWeather;
}