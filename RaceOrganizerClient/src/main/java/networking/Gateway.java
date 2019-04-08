package networking;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.ArrayList;
import java.util.List;

public abstract class Gateway {

    protected HttpPost request;
    protected String requestURL = "http://127.0.0.1:8000/";
    protected List<NameValuePair> params = new ArrayList<NameValuePair>();
    protected CloseableHttpClient client;
}
