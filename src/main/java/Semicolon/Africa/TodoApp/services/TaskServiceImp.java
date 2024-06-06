package Semicolon.Africa.TodoApp.services;


import Semicolon.Africa.TodoApp.Data.Model.Task;
import Semicolon.Africa.TodoApp.Data.Model.User;
import Semicolon.Africa.TodoApp.Data.Repository.TaskRepository;
import Semicolon.Africa.TodoApp.Data.Repository.UserRepository;
import Semicolon.Africa.TodoApp.dtos.request.*;
import Semicolon.Africa.TodoApp.dtos.response.AddTaskResponse;
import Semicolon.Africa.TodoApp.dtos.response.DeleteTaskResponse;
import Semicolon.Africa.TodoApp.dtos.response.UpdateTaskResponse;
import Semicolon.Africa.TodoApp.dtos.response.ShareTaskResponse;
import Semicolon.Africa.TodoApp.utils.MailConfig;
import Semicolon.Africa.TodoApp.utils.TodoAppException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static Semicolon.Africa.TodoApp.utils.Mapper.*;
import static Semicolon.Africa.TodoApp.utils.Validators.*;


@Service
@AllArgsConstructor
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final MailConfig mailConfig;

    private final MailService mailService;

    public AddTaskResponse addTask(AddTaskRequest addTaskRequest) {
        addTaskValidation(addTaskRequest);
        Task newTask = map(addTaskRequest);
        Task savedTask = taskRepository.save(newTask);
        addTaskUserVerification(addTaskRequest, savedTask);
        sendTaskEmail(savedTask.getEmail(), "Your task have been added successfully");
        return mapAddTaskResponse();
    }

    private void sendTaskEmail(String email,String message) {
        JavaMailRequest javaMailRequest = new JavaMailRequest();
        javaMailRequest.setTo(email);
        javaMailRequest.setMessage(message);
        javaMailRequest.setSubject("TodoApp Confirmation");
        javaMailRequest.setFrom(mailConfig.getFromEmail());
        mailService.send(javaMailRequest);
    }

    @Override
    public UpdateTaskResponse updateTask(TaskUpdateRequest taskUpdateRequest) {
        updatedTaskValidation(taskUpdateRequest);
        Task updatedTask = updateTaskUserVerification(taskUpdateRequest);
        taskRepository.save(updatedTask);
        return mapEditTaskResponse();
    }

    @Override
    public DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest) {
        Task task = taskRepository.findTaskByTitle(deleteTaskRequest.getTitle());
        if(task==null)throw new TodoAppException("Task not found");
        taskRepository.delete(task);
        return mapDeleteTaskResponse();
    }

    @Override
    public List<Task> viewAllTask() {
         return taskRepository.findAll();
    }

    @Override
    public ShareTaskResponse shareTask(ShareTaskRequest shareTaskRequest) {
        shareTaskValidation(shareTaskRequest);
        Task task = findTaskByTitle(shareTaskRequest.getTitle());
        task.setReceiver(shareTaskRequest.getReceiver());
        taskRepository.save(task);
        assignTaskToUserConfirmation(shareTaskRequest, task);
        sendTaskEmail(task.getEmail(), "Your task have been shared successfully");

        return getShareTaskResponse();
    }


    private void addTaskUserVerification(AddTaskRequest addTaskRequest, Task savedTask) {
        User user = findByUsername(addTaskRequest.getUsername());
        List<Task> tasks= user.getTasks();
        tasks.add(savedTask);
        user.setTasks(tasks);
        userRepository.save(user);
    }
    private Task updateTaskUserVerification(TaskUpdateRequest taskUpdateRequest) {
        Task task = taskRepository.findTaskByTitle(taskUpdateRequest.getTitle());
        if(task==null)throw new TodoAppException("Task not found");
        if (!task.getUsername().equals(taskUpdateRequest.getUsername()))
            throw new TodoAppException("Task not found for username");
        return map(taskUpdateRequest, task);
    }
    private void assignTaskToUserConfirmation(ShareTaskRequest shareTaskRequest, Task task) {
        User user = findByUsername(shareTaskRequest.getReceiver());
        List<Task> tasks= user.getTasks();
        for (Task t : tasks) {
            if (t.getTitle().equals(task.getTitle())) throw new TodoAppException("Task already exist");}
        tasks.add(task);
        user.setTasks(tasks);
        userRepository.save(user);
    }



    private Task findTaskByTitle(String title) {
         Task task = taskRepository.findTaskByTitle(title);
        if (task == null) throw new TodoAppException("Task not found for user with:" + title);
        return task;
    }
    @Override
    public Task viewTaskByTitle(ViewTaskByTitleRequest viewTaskByTitleRequest) {
        Task task = taskRepository.findTaskByTitle(viewTaskByTitleRequest.getTitle());
        if (task == null) throw new TodoAppException("Task not found for user with:" + viewTaskByTitleRequest.getTitle());
        return task;
    }

    private User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new TodoAppException("User not found");
        } else {
            return user;
        }
    }
}



