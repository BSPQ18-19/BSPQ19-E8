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
        /* Test Successful Login */

        Assertions.assertTrue(gateway.login("a.santiago", "test"));

        /* Test failed login */
        gateway = new AuthGateway();
        Assertions.assertFalse(gateway.login("test", "1234"));
    }

    @Test
    void logout() {

        /* Test failed logout without login */
        Assertions.assertFalse(gateway.logout());

        /* Test Successful Logout */
        gateway.login("a.santiago", "test");
        Assertions.assertFalse(gateway.logout());
    }
}