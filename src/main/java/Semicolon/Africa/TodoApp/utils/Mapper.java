package Semicolon.Africa.TodoApp.utils;

import Semicolon.Africa.TodoApp.Data.Model.Status;
import Semicolon.Africa.TodoApp.Data.Model.Task;
import Semicolon.Africa.TodoApp.Data.Model.User;
import Semicolon.Africa.TodoApp.dtos.request.*;
import Semicolon.Africa.TodoApp.dtos.response.*;
import java.time.LocalDate;
import static Semicolon.Africa.TodoApp.utils.Encryptor.encryptPassword;


public class Mapper {



    public static User map(RegisterUserRequest registerUserRequest ) {

        User user = new User();
        user.setUsername(registerUserRequest.getUsername().toLowerCase());
        user.setPassword(encryptPassword(registerUserRequest.getPassword()));

        return user;
    }


    public static RegisterUserResponse map(User user){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration successful" + user);
        return registerUserResponse;
    }

    public static User userMapper(String userName, String password, String email) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setLoggedIn(false);
        return user;
    }

    public static Task map(AddTaskRequest addTaskRequest) {
        Task newTask = new Task();
        newTask.setTitle(addTaskRequest.getTitle());
        newTask.setDescription(addTaskRequest.getDescription());
        newTask.setStatus(Status.CREATED);
        newTask.setPriority(addTaskRequest.getPriority());
        newTask.setDueDate(LocalDate.from(addTaskRequest.getDueDate()));
        newTask.setUsername(addTaskRequest.getUsername());
        newTask.setSender(addTaskRequest.getUsername());

        return newTask;
    }


    public static AddTaskResponse mapAddTaskResponse() {
        AddTaskResponse response = new AddTaskResponse();
        response.setMessage("Task added successfully");
        return response;
    }



    public static UpdateTaskResponse mapEditTaskResponse() {
        UpdateTaskResponse response = new UpdateTaskResponse();
        response.setMessage("Task updated successfully");


        return response;

    }
        public static DeleteTaskResponse mapDeleteTaskResponse() {
            DeleteTaskResponse response = new DeleteTaskResponse();
        response.setMessage("Task updated successfully");


        return response;
    }
    public static Task map(TaskUpdateRequest taskUpdateRequest, Task task) {
        task.setTitle(taskUpdateRequest.getTitle().toLowerCase());
        task.setDescription(taskUpdateRequest.getDescription().toLowerCase());
        task.setStatus(Status.PENDING);
        task.setUsername(taskUpdateRequest.getUsername().toLowerCase());
        task.setPriority(taskUpdateRequest.getPriority());
        task.setDueDate(LocalDate.from(taskUpdateRequest.getDueDate()));

        return task;
    }


    public static ShareTaskResponse getShareTaskResponse() {
        ShareTaskResponse shareTaskResponse = new ShareTaskResponse();
        shareTaskResponse.setMessage("Task shared successfully");
        return shareTaskResponse;
    }

}
