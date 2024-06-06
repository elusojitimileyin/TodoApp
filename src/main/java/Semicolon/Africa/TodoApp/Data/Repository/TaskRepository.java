package Semicolon.Africa.TodoApp.Data.Repository;

import Semicolon.Africa.TodoApp.Data.Model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface TaskRepository extends MongoRepository<Task, String> {

    boolean existsByTitleAndUsername(String title, String username);
    Task findTaskByTitle(String title);
    Task findTaskById(String id);

}
