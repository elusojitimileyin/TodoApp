package Semicolon.Africa.TodoApp.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class JavaMailRequest {

    private String to;

    private String subject;

    private String message;

    private String from = "elusojitimileyin@gmail.com";

}
