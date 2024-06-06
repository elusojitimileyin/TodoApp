package Semicolon.Africa.TodoApp.dtos.request;

import Semicolon.Africa.TodoApp.Data.Model.Priority;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  AddTaskRequest {
    private String title;
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private String username;



}
