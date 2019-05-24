package managment;

import networking.RestGateway;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class AuthManagement {

    private static Logger log = LogManager.getLogger(AuthManagement.class.getName());

    /**
     * POST: /login/
     *
     * @param username username of the user
     * @param password password of the user
     * @return boolean depending on the success of the API query
     */
    public static boolean login(String username, String password) {

        HashMap<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        int responseCode = RestGateway.getInstance().post("login/", data);

        boolean success = responseCode == 200;

        if (success) {
            log.info("Successfully logged in");
        } else {
            log.error("Error login in " + responseCode);
        }

        return (success);
    }

    /**
     * GET: /login/
     *
     * @return boolean depending on the success of the API query
     */
    public static boolean logout() {

        HashMap<String, String> responseData = RestGateway.getInstance().get("logout/");

        int responseCode = Integer.parseInt(responseData.get("response_code"));

        boolean success = responseCode == 200;

        if (success) {
            RestGateway.getInstance().flushSession(); /*Flush session cookies to avoid further errors */
            log.info("Successfully logged out");
        } else {
            log.error("Error login out " + responseCode);
        }

        return (success);  /*if user is not logged in at the end of logout we assume logout succeeded */
    }

    /**
     * POST: /signup/
     *
     * @param username    username of the new user
     * @param password    password of the new user
     * @param firstName   first name of the new user
     * @param lastname    last name of the new user
     * @param email       email of the new user
     * @param personal_id personal id of the new user
     * @param birth_date  birth date of the new user
     * @return boolean depending on the success of the API query
     */
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

        boolean success = responseCode == 201;

        if (success) {
            log.info("Successfully registered new user");
        } else {
            log.error("Error registering new user " + responseCode);
        }

        return (success);
    }

}
