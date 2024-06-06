package Semicolon.Africa.TodoApp.controller;

import Semicolon.Africa.TodoApp.Data.Model.User;
import Semicolon.Africa.TodoApp.dtos.request.LoginUserRequest;
import Semicolon.Africa.TodoApp.dtos.request.LogoutUserRequest;
import Semicolon.Africa.TodoApp.dtos.request.RegisterUserRequest;
import Semicolon.Africa.TodoApp.dtos.response.*;
import Semicolon.Africa.TodoApp.services.UserService;
import Semicolon.Africa.TodoApp.utils.TodoAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TodoApp_User")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            RegisterUserResponse result = userService.registerUser(registerUserRequest);
            return new ApiResponse(true, result);
        } catch (TodoAppException message) {
            return new ApiResponse(false, message.getMessage());
        }
    }
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            LoginUserResponse result = userService.login(loginUserRequest);
                return new ApiResponse(true,  result);
        }
        catch (TodoAppException message) {
            return new ApiResponse(false, message.getMessage());
        }
    }

    @PostMapping("/logout")
    public ApiResponse logout(@RequestBody LogoutUserRequest logoutUserRequest) {
        try {
            LogoutUserResponse result = userService.logout(logoutUserRequest);
            return new ApiResponse(true,  result);
        }
        catch (TodoAppException message) {
            return new ApiResponse(false,  message.getMessage());
        }
    }

    @GetMapping("/get_user")
    public ApiResponse getAllUser() {
        try {
            List<User> result = userService.getAllUser();
            return new ApiResponse(true, result);
        } catch (TodoAppException message) {
            return new ApiResponse(false, message.getMessage());
        }
    }

}