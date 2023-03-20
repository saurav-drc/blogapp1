
package com.blogger.blogapp.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor

public class UserDto {
    private long id;
    @NotEmpty
    @Size(min=3,message = "name must be minimum of 3 characters")
    private String name;
    @Email(message = "invalid email id")
    private String email;
    @NotEmpty
    @Size(min=3,max=10,message = "password should be between 3 to 10 characters")
    private String password;
    @NotEmpty
    private String about;
}
