package networking;

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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RestGateway {


    private static Logger log = Logger.getLogger(RestGateway.class.getName());

    private static CookieStore cookieStore = null;
    private static HttpContext httpContext = new BasicHttpContext();
    private String host;
    private String requestURL;
    private List<NameValuePair> params = new ArrayList<>();
    private CloseableHttpClient client;
    private File requestURLFile = new File("config/serverhost.txt");

    private static RestGateway instance = new RestGateway();

    private RestGateway() {
        String result = "";

        client = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        if (cookieStore == null) {
            cookieStore = new BasicCookieStore();
            httpContext = new BasicHttpContext();
            httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        }
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(requestURLFile));
            while ((this.host = bfr.readLine()) != null) {  // read and store only line
                result = host;
            }
            bfr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
        host = result;
    }

    public static RestGateway getInstance() {
        return instance;
    }

    /**
     * Clears all cookies
     */
    public void flushSession() {
        cookieStore = new BasicCookieStore();
        httpContext = new BasicHttpContext();
        httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
    }

    /**
     * Send GET request
     *
     * @param url target url
     * @return response code of the request
     */
    public HashMap<String, String> get(String url) {

        requestURL = host + url;

        HttpGet getRequest = new HttpGet(requestURL);

        HttpResponse response = null;

        try {
            response = client.execute(getRequest, httpContext);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        assert response != null;
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

        StringBuilder result = new StringBuilder();
        String line;

        try {
            assert rd != null;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }

        HashMap<String, String> responseData = new HashMap<>();

        responseData.put("response_code", Integer.toString(responseCode));
        responseData.put("result", result.toString());

        log.info(" - RestGateway: GET " + requestURL + " " + responseCode);

        return responseData;
    }

    /**
     * Send POST request
     *
     * @param url target url
     * @param data data to send in the post
     * @return response code of the request
     */
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

        HttpPost postRequest = new HttpPost(requestURL);

        /* POST Request initialisation end */
        int responseCode = -1;
        try {
            postRequest.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        try {
            CloseableHttpResponse response = client.execute(postRequest, httpContext);
            responseCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        log.info(" - RestGateway: POST " + requestURL + " " + responseCode);

        return responseCode;
    }

    /**
     * Send PUT request
     *
     * @param url target url
     * @param json data to send in the put
     * @return response code of the request
     */
    public int put(String url, String json) {

        requestURL = host + url;

        client = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        HttpPut putRequest = new HttpPut(requestURL);

        try {
            putRequest.setEntity(new StringEntity(json));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        putRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        int responseCode = -1;

        try (CloseableHttpResponse httpResponse = client.execute(putRequest)) {

            responseCode = httpResponse.getStatusLine().getStatusCode();
        } catch (IOException e) {
            //handle exception
            log.error(e.getMessage());
        }

        log.info(" - RestGateway: PUT " + requestURL + " " + responseCode);

        return responseCode;
    }
}
