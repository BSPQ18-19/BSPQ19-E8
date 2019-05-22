package managment;

import managment.AuthManagement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthManagementTest {

    @Test
    void login() {
        /* Test failed login */
        Assertions.assertFalse(AuthManagement.login("test", "1234"));

        /* Test Successful login */
        Assertions.assertTrue(AuthManagement.login("a.santiago", "test"));
    }

    @Test
    void logout() {
        Assertions.assertFalse(AuthManagement.logout());

        /* Test Successful logout */
        AuthManagement.login("a.santiago", "test");
        Assertions.assertTrue(AuthManagement.logout());
    }

    @Test
    void register() {
        /* Test Successful sign up */
        Assertions.assertTrue(AuthManagement.register("test", "numero123", "first",
                "last", "email@email.com", "11111111T", "1995-05-01"));
    }
}