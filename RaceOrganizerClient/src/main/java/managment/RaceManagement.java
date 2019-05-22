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
    private static String dateString = formatter.format(new Date(System.currentTimeMillis()));

    /**
     * GET api/races
     **/

    public static Race[] getRaces() {

        Gson gson = new Gson();

        HashMap responseData = RestGateway.getInstance().get("api/races/");

        System.out.println("Response: " + responseData.get("result"));

        return gson.fromJson(responseData.get("result").toString(), Race[].class);
    }

    /**
     * @param r to add
     * @return True if race is added correctly
     */
    public static boolean addRace(Race r) {

        HashMap<String, String> data = new HashMap<>();
        data.put("edition", r.getEdition());
        data.put("sponsor", r.getSponsor());
        data.put("place", r.getPlace());
        System.out.println(dateString);
        data.put("time", dateString);
        data.put("price", ((int) r.getPrice()) + "");
        data.put("prize", ((int) r.getPrize()) + "");

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

        System.out.println("Response: " + responseData.get("result"));

        return gson.fromJson(responseData.get("result").toString(), Race.class);

    }

    /**
     * POST api/races/{race_id}/add_helper  && POST api/races/{race_id}/add_runner
     **/

    public static boolean addUserToRace(User u, Race r, int type) {

        String url;

        if (type == USER_RUNNER) {
            url = "api/races/" + r.getRace_id() + "/add_runner";
        } else if (type == USER_HELPER) {
            url = "api/races/" + r.getRace_id() + "/add_helper";
        } else {
            return false;
        }

        HashMap<String, String> data = new HashMap<>();
        data.put("username", u.getUsername());

        int responseCode = RestGateway.getInstance().post(url, data);

        return (responseCode == 201);
    }
}
