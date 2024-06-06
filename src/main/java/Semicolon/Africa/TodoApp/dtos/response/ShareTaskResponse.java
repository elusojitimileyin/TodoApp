package Semicolon.Africa.TodoApp.dtos.response;

import Semicolon.Africa.TodoApp.Data.Model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;





@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ShareTaskResponse {
    private String message;

}
