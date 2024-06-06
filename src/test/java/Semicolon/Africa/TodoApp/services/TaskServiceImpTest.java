package Semicolon.Africa.TodoApp.services;

import Semicolon.Africa.TodoApp.Data.Model.Priority;
import Semicolon.Africa.TodoApp.Data.Model.Status;
import Semicolon.Africa.TodoApp.Data.Repository.TaskRepository;
import Semicolon.Africa.TodoApp.Data.Repository.UserRepository;
import Semicolon.Africa.TodoApp.dtos.request.*;
import Semicolon.Africa.TodoApp.utils.TodoAppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TaskServiceImpTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskService taskService;




    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        taskRepository.deleteAll();

        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("@Twi1234");
        userRegisterRequest.setEmail("tobantu@gmail.com");
        userService.registerUser(userRegisterRequest);
    }



    @Test
    public void testToRegisterOneUser_userIsNotLogin_UserCannotAddTask() {

        assertEquals(1, userRepository.count());

        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setUsername("username");
        addTaskRequest.setTitle("title");
        addTaskRequest.setDescription("description");
        addTaskRequest.setDueDate(LocalDate.parse("2024-12-12"));
        addTaskRequest.setPriority(Priority.HIGH_PRIORITY);

        assertThrows(TodoAppException.class, () -> taskService.addTask(addTaskRequest));
    }

    @Test
    public void testToRegisterOneUser_userCanLogin_UserCanAddTask() {

        assertEquals(1, userRepository.count());

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);

        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setUsername("username");
        addTaskRequest.setTitle("title");
        addTaskRequest.setDescription("description");
        addTaskRequest.setDueDate(LocalDate.parse("2024-12-12"));
        addTaskRequest.setPriority(Priority.HIGH_PRIORITY );
        taskService.addTask(addTaskRequest);
        assertEquals(1, taskRepository.count());
    }
    @Test
    public void testToRegisterMoreThanOneUser_userIsLogin_AddMoreTask() {


        RegisterUserRequest userRegisterRequest1 = new RegisterUserRequest();
        userRegisterRequest1.setUsername("username1");
        userRegisterRequest1.setPassword("@Twi1234");
        userRegisterRequest1.setEmail("tobantu@gmail.com");
        userService.registerUser(userRegisterRequest1);
        assertEquals(2, userRepository.count());

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);

        LoginUserRequest loginUserRequest1 = new LoginUserRequest();
        loginUserRequest1.setUsername("username1");
        loginUserRequest1.setPassword("@Twi1234");
        userService.login(loginUserRequest1);

        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setUsername("username");
        addTaskRequest.setTitle("title");
        addTaskRequest.setDescription("description");
        addTaskRequest.setDueDate(LocalDate.parse("2024-12-12"));
        addTaskRequest.setPriority(Priority.HIGH_PRIORITY );

        taskService.addTask(addTaskRequest);

        AddTaskRequest addTaskRequest1 = new AddTaskRequest();
        addTaskRequest1.setUsername("username1");
        addTaskRequest1.setTitle("title1");
        addTaskRequest1.setDescription("description1");
        addTaskRequest1.setDueDate(LocalDate.parse("2024-12-12"));
        addTaskRequest1.setPriority(Priority.HIGH_PRIORITY );

        taskService.addTask(addTaskRequest1);
        assertEquals(2, taskRepository.count());
    }
    @Test
    void testToRegisterOneUser_userCanLogin_UserCanAddTask_UserCanDeleteTask() {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);

        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setUsername("username");
        addTaskRequest.setTitle("title1");
        addTaskRequest.setDescription("description");
        addTaskRequest.setDueDate(LocalDate.parse("2024-12-12"));
        addTaskRequest.setPriority(Priority.HIGH_PRIORITY );
        taskService.addTask(addTaskRequest);

        assertEquals(1, taskRepository.count());

        DeleteTaskRequest deleteTaskRequest = new DeleteTaskRequest();
        deleteTaskRequest.setTitle("title1");
        taskService.deleteTask(deleteTaskRequest);

        assertEquals(0, taskRepository.count());

    }
    @Test
    void testToRegisterOneUser_userCanLogin_UserCanAddTask_userCanUpdateTask() {

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);


        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setUsername("username");
        addTaskRequest.setTitle("title");
        addTaskRequest.setDescription("description");
        addTaskRequest.setDueDate(LocalDate.parse("2024-12-12"));
        addTaskRequest.setPriority(Priority.HIGH_PRIORITY );

        taskService.addTask(addTaskRequest);


        assertEquals(1, taskRepository.count());

        TaskUpdateRequest taskUpdateRequest = new TaskUpdateRequest();
        taskUpdateRequest.setUsername("username");
        taskUpdateRequest.setTitle("title");
        taskUpdateRequest.setDescription("description1");
        taskUpdateRequest.setDueDate(LocalDate.parse("2024-12-12"));
        taskUpdateRequest.setPriority(Priority.HIGH_PRIORITY );
        taskUpdateRequest.setStatus(Status.PENDING);
        taskService.updateTask(taskUpdateRequest);

        assertEquals(1, taskRepository.count());

    }


    @Test
    void testToRegisterTwoUser_userOneIsLogin_userCanAddTask_UserCanShareTask() {

        RegisterUserRequest userRegisterRequest1 = new RegisterUserRequest();
        userRegisterRequest1.setUsername("username1");
        userRegisterRequest1.setPassword("@Twi1234");
        userRegisterRequest1.setEmail("tobantu@gmail.com");
        userService.registerUser(userRegisterRequest1);
        assertEquals(2, userRepository.count());

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);

        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setUsername("username");
        addTaskRequest.setTitle("title");
        addTaskRequest.setDescription("description");
        addTaskRequest.setDueDate(LocalDate.parse("2024-12-12"));
        addTaskRequest.setPriority(Priority.HIGH_PRIORITY );
        taskService.addTask(addTaskRequest);

        ShareTaskRequest shareTaskRequest = new ShareTaskRequest();
        shareTaskRequest.setSender("username");
        shareTaskRequest.setTitle("title");
        shareTaskRequest.setReceiver("username1");
        taskService.shareTask(shareTaskRequest);
        assertEquals(1, taskRepository.count());

    }



    }