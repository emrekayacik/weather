package com.emrekayacik.weather.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    @Length(min = 3,max = 25,message = "Username must be between 2-25 characters.")
    private  String username;
    @NotBlank @Length(min = 3,max = 25)
    private String password;
    @NotBlank @Length(min = 2,max = 50)
    private String name;
    @NotBlank @Length(min = 2,max = 50)
    private String surname;
    @Email
    private String email;
    @NotBlank @Length(min = 3,max = 30)
    private String phoneNumber;

}
