package Semicolon.Africa.TodoApp.dtos.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class RegisterUserRequest {
    private String username;
    private String password;
    private String email;

}
