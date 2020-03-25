package com.kzadha.model.json;

import com.kzadha.model.enums.UserRole;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequest {
    @NotNull(message = "password is compulsory")
    @NotBlank(message = "password is compulsory")
    @Size(max = 30,message = "maximum size of password is 30")
    private String password;
    @NotNull(message = "name is compulsory")
    @NotBlank(message = "name is compulsory")
    @Size(max = 100,message = "maximum size of firstName is 30")
    private String name;
    @NotNull(message = "email is compulsory")
    @NotBlank(message = "email is compulsory")
    @Size(max = 50, message = "maximum size of email is 50")
    @Email(message = "email is not valid")
    private String email;
//    @NotNull(message = "captcha is compulsory")
//    @NotBlank(message = "captcha is compulsory")
//    private String captcha;
    private UserRole role;
    private String status;
}
