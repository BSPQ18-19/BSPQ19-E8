package networking;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import models.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class UsersGateway extends Gateway {

	

    public UsersGateway() {
        
        client = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
      }
    
    
    public ArrayList<User> getUsers() {
    	ArrayList<User> listusers=new ArrayList<>();
    	
    	requestURL += "api/users";
    	request = new HttpPost(requestURL);
        
    	int responseCode = -1;
        try {
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            CloseableHttpResponse response = client.execute(request);
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
    	
    	requestURL += "api/users/"+user_id;
    	request = new HttpPost(requestURL);
        
    	int responseCode = -1;
        try {
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            CloseableHttpResponse response = client.execute(request);
            responseCode = response.getStatusLine().getStatusCode();
           
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (responseCode == 200) {
        	return user;
        }else {
        	return null;
        }
    }
    
    public User getUserInfo() {
    	User user=null;
    	
    	requestURL += "api/profile";
    	request = new HttpPost(requestURL);
        
    	int responseCode = -1;
        try {
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            CloseableHttpResponse response = client.execute(request);
            responseCode = response.getStatusLine().getStatusCode();
           
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (responseCode == 200) {
        	return user;
        }else {
        	return null;
        }
    }
}
