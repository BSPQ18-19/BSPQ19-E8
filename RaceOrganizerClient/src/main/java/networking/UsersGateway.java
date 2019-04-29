package networking;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class UsersGateway extends Gateway {

	

    public UsersGateway() {
    	this.init();
    }
    
    
    public ArrayList<User> getUsers() {
    	ArrayList<User> listusers=new ArrayList<>();
    	requestURL = host + "api/users";
    	postRequest = new HttpPost(requestURL);
        
    	int responseCode = -1;
        try {
            postRequest.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            CloseableHttpResponse response = client.execute(postRequest);
            responseCode = response.getStatusLine().getStatusCode();
           
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (responseCode == 200) {
        	return listusers;
        }else {
        	return null;
        }
    }
    
    public User getUserByID(int user_id) {
    	User user=null;
    	
    	requestURL = host + "api/users/"+user_id;
    	int responseCode = -1;
       	System.out.println(requestURL);

    	client = HttpClientBuilder.create().build();
    	getRequest = new HttpGet(requestURL);
    	HttpResponse response = null;
    	try {
    		response = client.execute(getRequest, httpContext);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	responseCode =response.getStatusLine().getStatusCode();
    	System.out.println("Response Code : " + responseCode);
    	
    	/*Read response*/
    	BufferedReader rd = null;
		try {
			rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	StringBuffer result = new StringBuffer();
    	String line = "";
    	try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println("Response: " + result);
    	
    	return user;
    }
    
    public User getLoggedProfile() {
    	
    	User user = null;
    	int responseCode = -1;
    	
    	requestURL = host + "api/profile";
    	System.out.println(requestURL);

    	client = HttpClientBuilder.create().build();
    	getRequest = new HttpGet(requestURL);
    	HttpResponse response = null;
    	try {
    		response = client.execute(getRequest, httpContext);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	responseCode =response.getStatusLine().getStatusCode();
    	System.out.println("Response Code : " + responseCode);
    	
    	/*Read response*/
    	BufferedReader rd = null;
		try {
			rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	StringBuffer result = new StringBuffer();
    	String line = "";
    	try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println("Response: " + result);
    	
    	return user;
    }
}
