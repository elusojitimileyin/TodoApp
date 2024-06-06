package Semicolon.Africa.TodoApp.services;

import Semicolon.Africa.TodoApp.Data.Repository.UserRepository;
import Semicolon.Africa.TodoApp.dtos.request.*;
import Semicolon.Africa.TodoApp.dtos.response.*;
import Semicolon.Africa.TodoApp.utils.TodoAppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImpTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();

        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("@Twi1234");
        userRegisterRequest.setEmail("tobantu@gmail.com");
        userService.registerUser(userRegisterRequest);
    }

    @Test
    public void testThatUserCanRegisterOnce() {
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testThatUserCannotRegisterWithEmptyString() {
        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("");
        userRegisterRequest.setPassword("");
        userRegisterRequest.setEmail("tobantu@gmail.com");

        assertThrows(TodoAppException.class, () -> userService.registerUser(userRegisterRequest));
    }

    @Test
    void testThatUserCanLoginAfterRegistration() {
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("@Twi1234");
        LoginUserResponse loginResponse = userService.login(loginRequest);

        assertNotNull(loginResponse);
        assertThat(loginResponse.getMessage(), is("Successful Login"));
    }

    @Test
    void testThatUserCanRegister_login_logout() {
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("@Twi1234");
        LoginUserResponse loginResponse = userService.login(loginRequest);

        assertNotNull(loginResponse);
        assertThat(loginResponse.getMessage(), is("Successful Login"));

        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("username");
        LogoutUserResponse logoutResponse = userService.logout(logoutRequest);

        assertNotNull(logoutResponse);
        assertThat(logoutResponse.getMessage(), is("Successful Logout"));
    }

    @Test
    void testThatICannotLoginIfIDontRegister() {
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("fela");
        loginRequest.setPassword("@Twi1234");

        assertThrows(TodoAppException.class, () -> userService.login(loginRequest));
    }

    @Test
    void testThatICannotLogoutIfIDontLogin() {
        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("fela");

        assertThrows(TodoAppException.class, () -> userService.logout(logoutRequest));
    }

    @Test
    void testThatUserCannotLoginWithWrongPasswordAfterRegistration() {
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("wrongPassword");

        assertThrows(TodoAppException.class, () -> userService.login(loginRequest),
                "Invalid password for user username");

        assertThat(userRepository.count(), is(1L));
    }


}
