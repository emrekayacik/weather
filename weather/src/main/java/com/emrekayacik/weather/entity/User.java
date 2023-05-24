package com.emrekayacik.weather.entity;

import com.emrekayacik.weather.base.entity.BaseEntity;
import com.emrekayacik.weather.security.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER_DEF")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}