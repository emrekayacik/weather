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
    @NotBlank(message = "Username cannot be blank.")
    @Length(min = 3,max = 25,message = "Username must be between 3-25 characters.")
    private  String username;
    @NotBlank(message = "Password cannot be blank.") @Length(min = 3,max = 25,message = "Password must be between 3-25 characters.")
    private String password;
    @NotBlank(message = "Name cannot be blank.") @Length(min = 2,max = 50,message = "Name must be between 2-50 characters.")
    private String name;
    @NotBlank(message = "Lastname cannot be blank.") @Length(min = 2,max = 50,message = "Lastname must be between 2-50 characters.")
    private String surname;
    @Email(message = "Email format is not correct. Please try the pattern youremail@domain.com") @NotBlank(message = "Email cannot be blank.")
    private String email;
    @NotBlank(message = "Phone Number cannot be blank.") @Length(min = 3,max = 30,message = "Phone Number must be between 3-30 characters.")
    private String phoneNumber;

}
