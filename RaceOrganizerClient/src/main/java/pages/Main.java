package pages;

import networking.LoginGateway;
import networking.SignupGateway;

public class Main {

    public static void main(String[] args) {
        LoginGateway gw = new LoginGateway("test", "test");
        System.out.println(gw.login());
        SignupGateway sw = new SignupGateway("usuario1234", "test", "test", "test",
                "usuario123@test.com", "52", "1998-05-02");
        System.out.println(sw.signUp());
    }

}
