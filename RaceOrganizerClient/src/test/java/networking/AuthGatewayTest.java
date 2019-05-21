package networking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AuthGatewayTest {

    private static AuthGateway gateway;

    @BeforeAll
    static void setUp() {
        gateway = new AuthGateway();
    }

    @Test
    void login() {
        /* Test failed login */
        gateway = new AuthGateway();
        Assertions.assertFalse(gateway.login("test", "1234"));

        /* Test Successful Login */
        Assertions.assertTrue(gateway.login("a.santiago", "test"));
    }

    @Test
    void logout() {
        /* Test failed logout without login */
        Assertions.assertFalse(gateway.logout());

        /* Test Successful Logout */
        gateway.login("a.santiago", "test");
        Assertions.assertFalse(gateway.logout());
    }

    @Test
    void register() {
        /* Test Successful sign up */
        Assertions.assertTrue(gateway.register("test", "numero123", "first",
                "last", "email@email.com", "11111111T", "1995-05-01"));
    }
}