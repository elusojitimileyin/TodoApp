package Semicolon.Africa.TodoApp.dtos.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewTaskByTitleRequest {
    private String title;
}
