package Semicolon.Africa.TodoApp.Data.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Document("Users")
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    @DBRef
    private List<Task> tasks = new ArrayList<>();
    private boolean isLoggedIn;
    private LocalDateTime dateRegistered = LocalDateTime.now();
    private LocalDateTime dateUpdated = LocalDateTime.now();
}
