package networking;

import models.Helper;
import models.Race;
import models.Runner;
import models.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RaceGateway extends Gateway {
	
	public final static int USER_RUNNER = 1;
	public final static int USER_HELPER = 2;
	
		public RaceGateway() {
			this.init();		
		}
		
		/** GET api/races **/
	
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
	    	System.out.println(result.toString());
	    	Race[] races = gson.fromJson(result.toString(), Race[].class);
	    	
	    	System.out.println("Response: " + result);
	    	
	    	return races;
		}
		
		/** @category POST api/races 
		 * @param r to add
		 * @return True if race is added correctly **/

		
		public boolean addRace(Race r) {
			
			/* POST Request initialisation */
	    	requestURL = host + "api/races/";
			System.out.println(requestURL);
			client = HttpClients.custom()
			        .setDefaultRequestConfig(RequestConfig.custom()
			                .setCookieSpec(CookieSpecs.STANDARD).build())
			        .build();
			params.add(new BasicNameValuePair("edition", r.getEdition()));
			params.add(new BasicNameValuePair("sponsor", r.getSponsor()));
			params.add(new BasicNameValuePair("place", r.getPlace()));
		    DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		    String dateString = formatter.format(new Date(System.currentTimeMillis()));
		    System.out.println(dateString);
			params.add(new BasicNameValuePair("time", dateString));
			params.add(new BasicNameValuePair("price", ((int)r.getPrice())+""));
			params.add(new BasicNameValuePair("prize",((int)r.getPrize())+""));
			
			
			postRequest = new HttpPost(requestURL);
			/* POST Request initialisation end */
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
		
		/** GET api/races/{race_id} **/

		
		public Race getRace(int race_id) {
			requestURL = host + "api/races/" + race_id;
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
					System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Race r = gson.fromJson(result.toString(), Race.class);

			return r;
	    	}
		
	/** POST api/races/{race_id}/add_helper  && POST api/races/{race_id}/add_runner **/
		
		public boolean addHelperToRace(User u, Race r, int type) {
			
			if(type ==  USER_RUNNER) {
				requestURL = host + "api/races/" + r.getRace_id() + "/add_runner";
			}else if(type ==  USER_HELPER) {
				requestURL = host + "api/races/" + r.getRace_id() + "/add_helper";
			}else {
				return false;
			}
			
			System.out.println(requestURL);
			client = HttpClients.custom()
			        .setDefaultRequestConfig(RequestConfig.custom()
			                .setCookieSpec(CookieSpecs.STANDARD).build())
			        .build();
			params.add(new BasicNameValuePair("username ", u.getUser_id()+""));
			System.out.println(u.getUsername());
			
			postRequest = new HttpPost(requestURL);
			/* POST Request initialisation end */
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
	        System.out.println(responseCode);
	        return true;
		}
		

	
	
}

