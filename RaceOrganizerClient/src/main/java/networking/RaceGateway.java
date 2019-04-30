package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import models.Race;
import models.User;

public class RaceGateway extends Gateway {
	
		public RaceGateway() {
			this.init();		
		}
	
		public Race[] getRaces() {
	    	requestURL = host + "api/races";
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
	    	
	    	Race[] races = gson.fromJson(result.toString(), Race[].class);
	    	
	    	System.out.println("Response: " + result);
	    	
	    	return races;
		}
		
		public boolean addRace(Race r) {
			return false;
		}
		
		public Race getRace(int id) {
			requestURL = host + "api/races/" + id;
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
	    	
	    	Race r = gson.fromJson(result.toString(), Race.class);
	    	
	    	System.out.println("Response: " + r.toString());
	    	
	    	return null;
	    	}
		
		public boolean addToRace(User u) {
			return false;
		}
	
	
}
