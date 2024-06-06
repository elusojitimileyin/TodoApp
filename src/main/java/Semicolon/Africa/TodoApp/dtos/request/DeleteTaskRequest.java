package Semicolon.Africa.TodoApp.dtos.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class DeleteTaskRequest {

    private String title;


}
