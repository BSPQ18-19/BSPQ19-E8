package networking;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import sun.net.www.http.HttpClient;

public class LoginGateway extends Gateway{
	
	
	public LoginGateway(String username, String password) {
		requestURL += "login/post/";
		client = HttpClients.custom()
		        .setDefaultRequestConfig(RequestConfig.custom()
		                .setCookieSpec(CookieSpecs.STANDARD).build())
		            .build();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		
		request = new HttpPost(requestURL);
		
	}
	
	public boolean login() {
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
	
			
		
		return (responseCode == 200);
	}

}
