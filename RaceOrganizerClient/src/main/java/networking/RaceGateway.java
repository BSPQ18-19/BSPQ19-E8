package networking;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import models.Race;
import models.User;

public class RaceGateway extends Gateway {
	
		public RaceGateway(String username, String password) {
			this.init();
			requestURL += "/login/";
		    System.out.println(requestURL);
		    client = HttpClients.custom()
		            .setDefaultRequestConfig(RequestConfig.custom()
		                    .setCookieSpec(CookieSpecs.STANDARD).build())
		            .build();
		    params.add(new BasicNameValuePair("username", username));
		    params.add(new BasicNameValuePair("password", password));
		
		    postRequest = new HttpPost(requestURL);
		
		}
	
		public Race[] getRaces() {
			
			return null;
		}
		
		public boolean addRace(Race r) {
			return false;
		}
		
		public Race getRace(int id) {
			return null;
		}
		
		public boolean addToRace(User u) {
			return false;
		}
	
	
}
