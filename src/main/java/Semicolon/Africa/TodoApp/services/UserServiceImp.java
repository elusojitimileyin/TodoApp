package Semicolon.Africa.TodoApp.services;

import Semicolon.Africa.TodoApp.Data.Model.User;
import Semicolon.Africa.TodoApp.Data.Repository.UserRepository;
import Semicolon.Africa.TodoApp.dtos.request.JavaMailRequest;
import Semicolon.Africa.TodoApp.dtos.request.LoginUserRequest;
import Semicolon.Africa.TodoApp.dtos.request.LogoutUserRequest;
import Semicolon.Africa.TodoApp.dtos.request.RegisterUserRequest;
import Semicolon.Africa.TodoApp.dtos.response.LoginUserResponse;
import Semicolon.Africa.TodoApp.dtos.response.LogoutUserResponse;
import Semicolon.Africa.TodoApp.dtos.response.RegisterUserResponse;
import Semicolon.Africa.TodoApp.utils.MailConfig;
import Semicolon.Africa.TodoApp.utils.TodoAppException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static Semicolon.Africa.TodoApp.utils.Encryptor.checkPassword;
import static Semicolon.Africa.TodoApp.utils.Encryptor.encryptPassword;
import static Semicolon.Africa.TodoApp.utils.Mapper.map;
import static Semicolon.Africa.TodoApp.utils.Mapper.userMapper;
import static Semicolon.Africa.TodoApp.utils.Validators.registeredUserValidation;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final MailConfig mailConfig;
    private final MailService mailService;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        registeredUserValidation(registerUserRequest);

        String username = registerUserRequest.getUsername();
        String password = encryptPassword(registerUserRequest.getPassword());
        String email = registerUserRequest.getEmail().toLowerCase().trim();

        User user = userMapper(username, password, email);
        sendRegistrationEmail(email);

        userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage("Registration successful");

        return response;
    }

    public void sendRegistrationEmail(String email) {
        JavaMailRequest javaMailRequest = new JavaMailRequest();
        javaMailRequest.setTo(email);
        javaMailRequest.setMessage("Welcome to my todo app");
        javaMailRequest.setSubject("TodoApp Verification");
        javaMailRequest.setFrom(mailConfig.getFromEmail());
        mailService.send(javaMailRequest);
    }

    @Override
    public LoginUserResponse login(LoginUserRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername().toLowerCase());
        if (user == null || !checkPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new TodoAppException("Invalid username or password");
        }

        user.setLoggedIn(true);
        userRepository.save(user);

        return new LoginUserResponse("Successful Login");
    }

    @Override
    public LogoutUserResponse logout(LogoutUserRequest logoutUserRequest) {
        User user = userRepository.findByUsername(logoutUserRequest.getUsername().toLowerCase());
        if (user == null) {
            throw new TodoAppException("User not found");
        }

        user.setLoggedIn(false);
        userRepository.save(user);

        return new LogoutUserResponse("Successful Logout");
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
