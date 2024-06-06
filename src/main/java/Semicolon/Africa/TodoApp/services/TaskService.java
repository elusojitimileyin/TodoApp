package Semicolon.Africa.TodoApp.services;

import Semicolon.Africa.TodoApp.Data.Model.Task;
import Semicolon.Africa.TodoApp.dtos.request.*;
import Semicolon.Africa.TodoApp.dtos.response.AddTaskResponse;
import Semicolon.Africa.TodoApp.dtos.response.DeleteTaskResponse;
import Semicolon.Africa.TodoApp.dtos.response.UpdateTaskResponse;
import Semicolon.Africa.TodoApp.dtos.response.ShareTaskResponse;

import java.time.DayOfWeek;
import java.util.List;

public interface TaskService {
 AddTaskResponse addTask(AddTaskRequest addTaskRequest);

 DeleteTaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest);

 Task viewTaskByTitle(ViewTaskByTitleRequest viewTaskByTitleRequest);

 List<Task> viewAllTask();

 ShareTaskResponse shareTask (ShareTaskRequest shareTaskRequest);


 UpdateTaskResponse updateTask(TaskUpdateRequest taskUpdateRequest);


}


