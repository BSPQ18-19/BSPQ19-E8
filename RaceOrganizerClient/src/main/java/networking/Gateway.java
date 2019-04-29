package networking;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Gateway {

    protected HttpPost postRequest;
    protected HttpGet getRequest;
    private File requestURLFile = new File("res/serverhost.txt");
    protected String host;
    protected String requestURL;
    protected static CookieStore cookieStore = null;
    protected static HttpContext httpContext = new BasicHttpContext();
    
    public void init(){
    	String result ="";
    	if(cookieStore == null) {
    		cookieStore =  new BasicCookieStore();
            httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
    	}
    	 try {
    		BufferedReader bfr = new BufferedReader(new FileReader(requestURLFile));
			while((this.host = bfr.readLine()) != null) {  // read and store only line    
				result = host;
			}
			bfr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 host = result; 
    }
    							
    protected List<NameValuePair> params = new ArrayList<NameValuePair>();
    protected CloseableHttpClient client;
}
