package managment;

import com.google.gson.Gson;
import models.Race;
import models.User;
import networking.RestGateway;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RaceManagement {

    private final static int USER_RUNNER = 1;
    private final static int USER_HELPER = 2;

    private static DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    /**
     * GET api/races
     **/

    public static Race[] getRaces() {

        Gson gson = new Gson();

        HashMap responseData = RestGateway.getInstance().get("api/races/");

        return gson.fromJson(responseData.get("result").toString(), Race[].class);
    }

    /**
     * @param r to add
     * @param datetime
     * @param price
     * @param prize
     * @return True if race is added correctly
     */
    public static boolean addRace(String edition, String sponsor, String place, Date datetime, int price, int prize) {

        HashMap<String, String> data = new HashMap<>();
        data.put("edition", edition);
        data.put("sponsor", sponsor);
        data.put("place", place);
        data.put("time", formatter.format(datetime));
        data.put("price", String.valueOf(price));
        data.put("prize", String.valueOf(prize));

        int responseCode = RestGateway.getInstance().post("api/races/", data);

        return (responseCode == 201);
    }

    /**
     * GET api/races/{race_id}
     * @param race_id
     * @return
     */
    public static Race getRace(int race_id) {

        Gson gson = new Gson();

        HashMap responseData = RestGateway.getInstance().get("api/races/" + race_id);

        return gson.fromJson(responseData.get("result").toString(), Race.class);

    }

    /**
     * POST api/races/{race_id}/add_helper  && POST api/races/{race_id}/add_runner
     **/

    public static boolean addUserToRace(String username, int race_id, int type) {

        String url;

        if (type == USER_RUNNER) {
            url = "api/races/" + race_id + "/add_runner";
        } else if (type == USER_HELPER) {
            url = "api/races/" + race_id + "/add_helper";
        } else {
            return false;
        }

        HashMap<String, String> data = new HashMap<>();
        data.put("username", username);

        int responseCode = RestGateway.getInstance().post(url, data);

        return (responseCode == 201);
    }
}
