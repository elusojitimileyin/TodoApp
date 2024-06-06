package Semicolon.Africa.TodoApp.Data.Repository;

import Semicolon.Africa.TodoApp.Data.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);

    User findByUsername(String Id);


}