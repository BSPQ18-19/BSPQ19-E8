package networking;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AuthGateway extends Gateway {

	public static void main(String[] args) {
		AuthGateway gw = new AuthGateway();
		//System.out.println(gw.login("test", "test"));
		System.out.println(gw.logout());
	}
	
	
    public AuthGateway() {
    	this.init();
    }

    public boolean login(String username, String password) {    	
    	/* POST Request initialisation */
    	requestURL += "login/";
		System.out.println(requestURL);
		client = HttpClients.custom()
		        .setDefaultRequestConfig(RequestConfig.custom()
		                .setCookieSpec(CookieSpecs.STANDARD).build())
		        .build();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		
		postRequest = new HttpPost(requestURL);
		/* POST Request initialisation end */
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


        return (responseCode == 200);
    }
    
    public boolean logout() {
    	int responseCode = -1;
    	requestURL += "logout/";
    	client = HttpClientBuilder.create().build();
    	getRequest = new HttpGet(requestURL);
    	HttpResponse response = null;
    	try {
    		response = client.execute(getRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());
    	return false;
    }

}
