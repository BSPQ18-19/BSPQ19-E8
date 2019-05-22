package managment;

import models.Race;
import models.Task;
import networking.RestGateway;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class TaskManagement {

    /**
     * @param r           Race to add task to
     * @param description Description of the task
     * @return True if task is added correctly, false if not
     * @category POST api/races/{race_id}/new_task
     **/

    public boolean addTask(Race r, String description) {

        HashMap<String, String> data = new HashMap<>();
        data.put("description", description);

        int responseCode = RestGateway.getInstance().post("api/races/" + r.getRace_id() + "/new_task", data);

        return (responseCode == 201);
    }

    //non modified parameters must be initl as null

//    public boolean editTask(Race r, Task t) {
//
//        HashMap<String, String> data = new HashMap<>();
//        data.put("description", t.getDescription());
//        data.put("description", t.getDescription());
//        data.put("description", t.getDescription());
//
//        String url = "api/races/" + r.getRace_id() + "/" + t.getTask_id() + r.getRace_id() + "/new_task";
//        int responseCode = RestGateway.getInstance().put(url, data);
//
//        return (responseCode == 200);
//
//    }

}
