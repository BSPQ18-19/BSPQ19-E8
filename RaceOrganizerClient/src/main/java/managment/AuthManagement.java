package managment;

import networking.RestGateway;

import java.util.HashMap;

public class AuthManagement {

    public static boolean login(String username, String password) {

        HashMap<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        int responseCode = RestGateway.getInstance().post("login/", data);

        return (responseCode == 200);
    }

    public static boolean logout() {

        HashMap<String, String> responseData = RestGateway.getInstance().get("logout/");

        int responseCode = Integer.parseInt(responseData.get("response_code"));


        boolean isLoggedIn = !(responseCode == 200);
        if (!isLoggedIn) RestGateway.getInstance().flushSession(); /*Flush session cookies to avoid further errors */
        return (!isLoggedIn);  /*if user is not logged in at the end of logout we assume logout succeeded */
    }

    public static boolean register(String username, String password, String firstName, String lastname, String email,
                                   String personal_id, String birth_date) {

        HashMap<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        data.put("first_name", firstName);
        data.put("last_name", lastname);
        data.put("email", email);
        data.put("personal_id", personal_id);
        data.put("birth_date", birth_date);

        int responseCode = RestGateway.getInstance().post("signup/", data);

        return (responseCode == 201);
    }

}
