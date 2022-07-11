package in.ankitapps.expensetrackerapi.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserModel {

    @NotBlank(message = "name must not be null")
    private String name;

    @NotNull(message = "email must not be null")
    @Email(message = "enter valid email")
    private String email;

    @Size(min=5,message = "password must be atleast 5 characters")
    private String password;

    private Long age=0L;
}
