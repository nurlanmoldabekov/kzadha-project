package com.kzadha.model;

import com.kzadha.model.enums.UserRole;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    @NotNull(message = "name is compulsory")
    @NotBlank(message = "name is compulsory")
    @Size(max = 255,message = "maximum size of name is 255")
    private String name;
    @NotNull(message = "email is compulsory")
    @NotBlank(message = "email is compulsory")
    @Size(max = 255,message = "maximum size of email is 255")
    private String email;
    @Size(max = 255,message = "maximum size of email is 255")
    private String password;
    @NotNull(message = "role is compulsory")
    private UserRole roles;

}
