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

import models.Organizer;
import models.Race;
import models.Runner;
import models.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class AuthGateway extends Gateway {
	

	public static void main(String[] args) {
		AuthGateway gw = new AuthGateway();
		System.out.println(gw.login("test", "test"));
		//UsersGateway ugw = new UsersGateway();
		/*Runner[] u  = new Runner[1];
		Organizer[] o = new Organizer[0];
		u[0] = new Runner(ugw.getLoggedProfile(), 5);
		Race myRace = new Race("4", "BBK", "Indonesia", new Date(Syst em.currentTimeMillis()), 15f, 1000f, u, o);
		rgw.addRace(myRace);
		Race[] serverRaces = rgw.getRaces();
		for(int i = 0; i < serverRaces.length; i++) {
			System.out.println(serverRaces[i].toString());
		}*/
//		TODO Commenting this to check

		RaceGateway rgw = new RaceGateway();
		TaskGateway tgw = new TaskGateway();
		Race race = rgw.getRace(2);
		//User user = ugw.getUserByID(3);
		tgw.addTask(race, "Test task 1");
		//rgw.addUserToRace(user, race, RaceGateway.USER_RUNNER);
		
		System.out.println(gw.logout());
		

	}
	
	
    public AuthGateway() {
    	this.init();
    }

    public boolean login(String username, String password) {    	
    	/* POST Request initialisation */
    	requestURL = host + "login/";
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
            CloseableHttpResponse response = client.execute(postRequest, httpContext);
            responseCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        isLoggedIn = (responseCode == 200);
        
        return isLoggedIn;
    }
    
    public boolean logout() {
    	int responseCode = -1;
    	requestURL = host + "logout/";
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
        isLoggedIn = !(responseCode == 200);
    	if(!isLoggedIn) flushSession(); /*Flush session cookies to avoid further errors */
    	return (!isLoggedIn);  /*if user is not logged in at the end of logout we assume logout succeeded */
    	
    }

}
