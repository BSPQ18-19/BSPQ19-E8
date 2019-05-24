package managment;

import com.google.gson.Gson;
import models.User;
import networking.RestGateway;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class UsersManagement {

    private static Logger log = LogManager.getLogger(UsersManagement.class.getName());

    /**
     * GET /api/users/
     *
     * @return list of Users with simplified information
     */
    public static User[] getUsers() {
        Gson gson = new Gson();

        HashMap responseData = RestGateway.getInstance().get("api/users/");

        int responseCode = Integer.parseInt((String) responseData.get("response_code"));

        boolean success = responseCode == 200;

        if (success) {
            log.info("Successfully got user list");
        } else {
            log.error("Error getting user list " + responseCode);
            return (new User[1]);
        }

        return gson.fromJson(responseData.get("result").toString(), User[].class);
    }

    /**
     * GET /api/user/{user_id}
     *
     * @param user_id id of the User
     * @return requested User
     */
    public static User getUser(int user_id) {

        Gson gson = new Gson();

        HashMap responseData = RestGateway.getInstance().get("api/users/" + user_id);

        int responseCode = Integer.parseInt((String) responseData.get("response_code"));

        boolean success = responseCode == 200;

        if (success) {
            log.info("Successfully got user");
        } else {
            log.error("Error getting user " + responseCode);
            return null;
        }

        return gson.fromJson(responseData.get("result").toString(), User.class);
    }

    /**
     * GET /api/profile/
     *
     * @return currently logged in User with detailed information
     */
    public static User getLoggedProfile() {

        Gson gson = new Gson();

        HashMap responseData = RestGateway.getInstance().get("api/profile/");

        int responseCode = Integer.parseInt((String) responseData.get("response_code"));

        boolean success = responseCode == 200;

        if (success) {
            log.info("Successfully got profile");
        } else {
            log.error("Error getting profile " + responseCode);
            return null;
        }

        return gson.fromJson(responseData.get("result").toString(), User.class);
    }
}
