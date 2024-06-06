package Semicolon.Africa.TodoApp.services;

import Semicolon.Africa.TodoApp.Data.Model.User;
import Semicolon.Africa.TodoApp.dtos.request.*;
import Semicolon.Africa.TodoApp.dtos.response.*;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    LoginUserResponse login(LoginUserRequest loginUserRequest);

    LogoutUserResponse logout(LogoutUserRequest logoutUserRequest);

    List<User> getAllUser();


}
