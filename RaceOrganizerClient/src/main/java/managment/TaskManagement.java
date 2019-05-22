package managment;

import com.google.gson.Gson;
import models.Task;
import networking.RestGateway;

import java.util.HashMap;

public class TaskManagement {

    /**
     * POST /api/races/{race_id}/new_task
     *
     * @param race_id     id for the race to add the task to
     * @param description description of the task
     * @return boolean depending on the success of the API query
     */
    static public boolean addTask(int race_id, String description) {

        HashMap<String, String> data = new HashMap<>();
        data.put("description", description);

        int responseCode = RestGateway.getInstance().post("api/races/" + race_id + "/new_task", data);

        return (responseCode == 201);
    }

    /**
     * PUT /api/races/{race_id}/{task_id}
     *
     * @param race_id id for the race the task is from
     * @param task    task class with the updated information
     * @return boolean depending on the success of the API query
     */
    static public boolean editTask(int race_id, Task task) {

        String json = new Gson().toJson(task);

        String url = "api/races/" + race_id + "/" + task.getTask_id() + "/";

        int responseCode = RestGateway.getInstance().put(url, json);

        return (responseCode == 200);
    }
}
