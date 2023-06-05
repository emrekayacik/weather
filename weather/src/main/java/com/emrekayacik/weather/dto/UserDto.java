package com.emrekayacik.weather.dto;

import com.emrekayacik.weather.base.dto.BaseDto;
import com.emrekayacik.weather.security.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    private Long id;
    @NotBlank(message = "Username cannot be blank.") @Length(min = 3,max = 25,message = "Username must be between 3-25 characters.")
    private  String username;
    @NotBlank(message = "Password cannot be blank.") @Length(min = 3,max = 25, message = "Password must be between 3-25 characters.")
    private String password;
    @NotBlank(message = "Name cannot be blank.") @Length(min = 2,max = 50,message = "Name must be between 2-50 characters.")
    private String name;
    @NotBlank(message = "Last name cannot be blank.") @Length(min = 2,max = 50,message = "Last name must be between 2-50 characters.")
    private String surname;
    @Email(message = "Email format is not correct. Please try the pattern <youremail@domain.com>") @NotBlank(message = "Email cannot be blank.")
    private String email;
    @NotBlank(message = "Phone number cannot be blank.") @Length(min = 3,max = 30,message = "Phone number must be between 3-30 characters.")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;

}