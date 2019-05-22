package managment;

import com.google.gson.Gson;
import models.User;
import networking.RestGateway;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class UsersManagement {

	/**
	 * GET /api/users/
	 *
	 * @return list of Users with simplified information
	 */
	public static User[] getUsers() {
		Gson gson = new Gson();

		HashMap responseData = RestGateway.getInstance().get("api/users/");

		return gson.fromJson(responseData.get("result").toString(), User[].class);
    }

	/**
	 * GET /api/user/{user_id}
	 *
	 * @param user_id id of the User
	 * @return requested User
	 */
	public static User getUser(int user_id) {

		Gson gson = new Gson();

		HashMap responseData = RestGateway.getInstance().get("api/users/" + user_id);

		return gson.fromJson(responseData.get("result").toString(), User.class);
    }

	/**
	 * GET /api/profile/
	 *
	 * @return currently logged in User with detailed information
	 */
	public static User getLoggedProfile() {

		Gson gson = new Gson();

		HashMap responseData = RestGateway.getInstance().get("api/profile/");

		return gson.fromJson(responseData.get("result").toString(), User.class);
    }
}
