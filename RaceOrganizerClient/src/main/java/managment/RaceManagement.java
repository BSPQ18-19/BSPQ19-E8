package managment;

import com.google.gson.Gson;
import models.Race;
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
     * GET api/races/{race_id}
     *
     * @return list of all races with minimal information
     */
    public static Race[] getRaces() {

        Gson gson = new Gson();

        HashMap responseData = RestGateway.getInstance().get("api/races/");

        return gson.fromJson(responseData.get("result").toString(), Race[].class);
    }

    /**
     * GET api/races/{race_id}
     *
     * @param race_id id for the requested race
     * @return requested race
     */
    public static Race getRace(int race_id) {

        Gson gson = new Gson();

        HashMap responseData = RestGateway.getInstance().get("api/races/" + race_id);

        return gson.fromJson(responseData.get("result").toString(), Race.class);

    }

    /**
     * POST /api/races/
     *
     * @param edition  unique name of the race
     * @param sponsor  who will be paying all race expenses
     * @param place    where the race will take place
     * @param datetime when will the race be
     * @param price    price of entry
     * @param prize    prize awarded
     * @return boolean depending on the success of the API query
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
     * POST /api/races/{race_id}/add_runner
     * POST /api/races/{race_id}/add_helper
     *
     * @param username username of the user to add
     * @param race_id  id for the race to add the user to
     * @param type     1 for adding the user as a runner and 2 as a helper
     * @return boolean depending on the success of the API query
     */
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