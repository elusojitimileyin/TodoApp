package Semicolon.Africa.TodoApp.dtos.request;

import Semicolon.Africa.TodoApp.Data.Model.Priority;
import Semicolon.Africa.TodoApp.Data.Model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter

public class TaskUpdateRequest {
    private String username;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;


}