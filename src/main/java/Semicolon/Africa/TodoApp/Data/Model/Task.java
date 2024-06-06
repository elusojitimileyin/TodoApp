package Semicolon.Africa.TodoApp.Data.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Document("Tasks")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Task {
    @Id
    private String id;
    private String username;
    private String title;
    private String description;
    private LocalDateTime dateTimeCreated = LocalDateTime.now();
    private LocalDateTime dateTimeUpdated = LocalDateTime.now();
    private LocalDate dueDate;
    private LocalDate currentDate;
    private Status status;
    private Priority priority;
    private String sender;
    private String receiver;
    private String email;

}
