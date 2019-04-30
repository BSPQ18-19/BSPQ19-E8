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

import com.google.gson.Gson;

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
    
    
    public User[] getUsers() {
    	requestURL = host + "api/users";
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
    	
    	User[] users = gson.fromJson(result.toString(), User[].class);
    	
    	
    	return users;
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
    	
    	user = gson.fromJson(result.toString(), User.class);
    	
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
    	
    	user = gson.fromJson(result.toString(), User.class);
    	
    	System.out.println("Response: " + result);
    	
    	return user;
    }
}
