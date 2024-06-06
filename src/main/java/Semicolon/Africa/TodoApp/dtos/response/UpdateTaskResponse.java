package Semicolon.Africa.TodoApp.dtos.response;

import Semicolon.Africa.TodoApp.Data.Model.Priority;
import Semicolon.Africa.TodoApp.Data.Model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UpdateTaskResponse {
    private String message;

}
