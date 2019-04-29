package networking;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

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
    protected String requestURL;
    
    public void init(){
    	String result ="";
    	 try {
    		BufferedReader bfr = new BufferedReader(new FileReader(requestURLFile));
			while((this.requestURL = bfr.readLine()) != null) {  // read and store only line    
				result = requestURL;
			}
			bfr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 requestURL = result; 
    }
    							
    protected List<NameValuePair> params = new ArrayList<NameValuePair>();
    protected CloseableHttpClient client;
}
