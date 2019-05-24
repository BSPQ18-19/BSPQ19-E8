package managment;

import com.google.gson.Gson;
import models.Task;
import networking.RestGateway;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class TaskManagement {

    private static Logger log = LogManager.getLogger(TaskManagement.class.getName());

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

        boolean success = responseCode == 201;

        if (success) {
            log.info("Successfully created new task");
        } else {
            log.error("Error creating new task " + responseCode);
        }

        return (success);
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

        boolean success = responseCode == 200;

        if (success) {
            log.info("Successfully modified task");
        } else {
            log.error("Error modifying task " + responseCode);
        }

        return (success);
    }
}
