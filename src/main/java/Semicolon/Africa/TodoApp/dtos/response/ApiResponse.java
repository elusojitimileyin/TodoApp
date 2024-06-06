package Semicolon.Africa.TodoApp.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse {

    boolean isSuccessful;
    Object Response;
}