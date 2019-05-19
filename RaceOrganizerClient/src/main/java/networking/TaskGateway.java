package networking;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import models.Race;
import models.Task;

public class TaskGateway extends Gateway {
	
	public TaskGateway() {
		this.init();		
	}
	
	/** @category POST api/races/{race_id}/new_task
	 * @param r Race to add task to
	 * @param description Description of the task
	 * @return True if task is added correctly, false if not **/
	
	public boolean addTask(Race r, String description) {
    	requestURL = host + "api/races/" + r.getRace_id() + "/new_task";
    	System.out.println(requestURL);
		client = HttpClients.custom()
		        .setDefaultRequestConfig(RequestConfig.custom()
		                .setCookieSpec(CookieSpecs.STANDARD).build())
		        .build();
		params.add(new BasicNameValuePair("description", description));
		
		postRequest = new HttpPost(requestURL);

		int responseCode = -1;
        try {
            postRequest.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            CloseableHttpResponse response = client.execute(postRequest, httpContext);
            responseCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		
        System.out.println("response:" + responseCode);
        
        return (responseCode == 201);
	}
	
	//non modified parameters must be initl as null
	
	public boolean editTask(Race r, Task t) {
		
	}

}
