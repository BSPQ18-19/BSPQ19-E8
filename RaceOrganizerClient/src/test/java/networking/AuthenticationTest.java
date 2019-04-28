package networking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthenticationTest {

    @Test
    void loginTest() {
        /* Test Successful Login */
        LoginGateway gw = new LoginGateway("test", "test");
        Assertions.assertTrue(gw.login());

        /* Test failed login */
        gw = new LoginGateway("test", "1234");
        Assertions.assertFalse(gw.login());
    }

    @Test
    void signupTest() {
        SignupGateway sw = new SignupGateway("usuario1234", "test", "test", "test",
                "usuario123@test.com", "52", "1998-05-02");

        /* Test Successful Registration */
        Assertions.assertTrue(sw.signUp(), "[username=usuario1234, password=test, first_name=test," +
                " last_name=test, email=usuario123@test.com, personal_id=52, birth_date=1998-05-02]");
        /* Test creating the same user again */
        Assertions.assertFalse(sw.signUp());
    }
}
