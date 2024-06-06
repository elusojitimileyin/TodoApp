package Semicolon.Africa.TodoApp.controller;

import Semicolon.Africa.TodoApp.Data.Model.Task;
import Semicolon.Africa.TodoApp.dtos.request.*;
import Semicolon.Africa.TodoApp.dtos.response.*;
import Semicolon.Africa.TodoApp.services.TaskService;
import Semicolon.Africa.TodoApp.utils.TodoAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/TodoApp_Task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/add_task")
    public ApiResponse addTask(@RequestBody AddTaskRequest addTaskRequest) {
        try {
            AddTaskResponse result = taskService.addTask(addTaskRequest);
            return new ApiResponse(true, result);
        } catch (TodoAppException message) {
            return new ApiResponse(false, message.getMessage());
        }
    }

    @PatchMapping("/update_Task")
    public ApiResponse updateTask(@RequestBody TaskUpdateRequest taskUpdateRequest) {
        try {
            UpdateTaskResponse result = taskService.updateTask(taskUpdateRequest);
            return new ApiResponse(true,  result);
        } catch (TodoAppException message) {
            return new ApiResponse(false, message.getMessage());
        }
    }

    @DeleteMapping("/delete_task")
    public ApiResponse deleteTask(@RequestBody DeleteTaskRequest deleteTaskRequest) {
        try {
            DeleteTaskResponse result = taskService.deleteTask(deleteTaskRequest);
            return new ApiResponse(true, result);
        } catch (TodoAppException message) {
            return new ApiResponse(false, message.getMessage());
        }
    }

    @PatchMapping("/share_Task")
    public ApiResponse shareTask(@RequestBody ShareTaskRequest shareTaskRequest) {
        try {
            ShareTaskResponse result = taskService.shareTask(shareTaskRequest);
            return new ApiResponse(true, result);
        } catch (TodoAppException message) {
            return new ApiResponse(false, message.getMessage());
        }
    }

    @GetMapping("/view_Tasks")
    public ApiResponse viewAllTasks() {
        try {
            List<Task> result = taskService.viewAllTask();
            return new ApiResponse(true,result);
        } catch (TodoAppException message) {
            return new ApiResponse(false, message.getMessage());
        }
    }

    @PostMapping("/viewTaskByTitle")
    public ApiResponse viewTaskByTitle(@RequestBody ViewTaskByTitleRequest viewTaskByTitleRequest) {
        try {
            List<Task> result = Collections.singletonList(taskService.viewTaskByTitle(viewTaskByTitleRequest));
            return new ApiResponse (true, result);
        } catch (TodoAppException message) {
            return new ApiResponse (false, message.getMessage());
        }
    }
}