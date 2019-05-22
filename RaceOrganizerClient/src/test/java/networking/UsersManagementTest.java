package networking;

import managment.AuthManagement;
import managment.UsersManagement;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UsersManagementTest {

    @BeforeAll
    static void setUp(){
        AuthManagement.login("a.santiago", "test");
    }

    @Test
    void getUsers() {
        User[] users = UsersManagement.getUsers();

        Assertions.assertTrue(users.length >= 5);
    }

    @Test
    void getUser() {
        User user = UsersManagement.getUser(1);

        Assertions.assertEquals(user.getUsername(), "john.doe");
        Assertions.assertEquals(user.getFirst_name(), "John");
        Assertions.assertEquals(user.getLast_name(), "Doe");
        Assertions.assertEquals(user.getEmail(), "john.doe@gmail.com");
    }

    @Test
    void getLoggedProfile() {
        User user = UsersManagement.getLoggedProfile();

        Assertions.assertEquals(user.getUsername(), "a.santiago");
        Assertions.assertEquals(user.getFirst_name(), "Amy");
        Assertions.assertEquals(user.getLast_name(), "Santiago");
        Assertions.assertEquals(user.getEmail(), "a.santiago@gmail.com");
    }
}