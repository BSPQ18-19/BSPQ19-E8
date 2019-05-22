package networking;

import com.google.gson.Gson;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RestGateway {

    private static CookieStore cookieStore = null;
    private static HttpContext httpContext = new BasicHttpContext();
    private HttpPost postRequest;
    private HttpGet getRequest;
    private HttpPut putRequest;
    private String host;
    private String requestURL;
    private List<NameValuePair> params = new ArrayList<NameValuePair>();
    private CloseableHttpClient client;
    private File requestURLFile = new File("res/serverhost.txt");

    private static RestGateway instance = new RestGateway();

    private RestGateway() {
        init();
    }

    public static RestGateway getInstance() {
        return instance;
    }

    public void flushSession() {
        cookieStore = new BasicCookieStore();
        httpContext = new BasicHttpContext();
        httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
    }

    private void init() {
        String result = "";

        if (cookieStore == null) {

        }
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(requestURLFile));
            while ((this.host = bfr.readLine()) != null) {  // read and store only line
                result = host;
            }
            bfr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        host = result;
    }

    public HashMap<String, String> get(String url) {

        requestURL = host + url;

        client = HttpClientBuilder.create().build();

        getRequest = new HttpGet(requestURL);

        HttpResponse response = null;

        try {
            response = client.execute(getRequest, httpContext);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int responseCode = response.getStatusLine().getStatusCode();

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

        HashMap<String, String> responseData = new HashMap<>();

        responseData.put("response_code", Integer.toString(responseCode));
        responseData.put("result", result.toString());

        System.out.println("GET: " + requestURL + " " + responseCode);

        return responseData;
    }


    public int post(String url, HashMap<String, String> data) {
        /* POST Request initialisation */
        requestURL = host + url;

        client = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        for (String parameter : data.keySet()) {
            params.add(new BasicNameValuePair(parameter, data.get(parameter)));
        }

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

        System.out.println("POST: " + requestURL + " " + responseCode);

        return responseCode;
    }

    public int put(String url, HashMap<String, String> data) {

        requestURL = host + "api/races/" + url;
        System.out.println(requestURL);

        for (String parameter : data.keySet()) {
            params.add(new BasicNameValuePair(parameter, data.get(parameter)));
        }

        putRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        try {
            postRequest.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int responseCode = -1;
        try (CloseableHttpResponse httpResponse = client.execute(putRequest)) {
            String content = EntityUtils.toString(httpResponse.getEntity());

            responseCode = httpResponse.getStatusLine().getStatusCode();
        } catch (IOException e) {
            //handle exception
            e.printStackTrace();
        }

        return responseCode;
    }
}
