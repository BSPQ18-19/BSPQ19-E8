package networking;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SignupGateway extends Gateway {

    public SignupGateway(String username, String password, String firstName, String lastname, String email, String personal_id, String birth_date) {
        requestURL += "signup/post/";
        client = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("first_name", firstName));
        params.add(new BasicNameValuePair("last_name", lastname));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("personal_id", personal_id));
        params.add(new BasicNameValuePair("birth_date", birth_date));

        System.out.println(params.toString());

        request = new HttpPost(requestURL);

    }

    public boolean signUp() {
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


        return (responseCode == 201);
    }


}
