package models;

public class Helper extends User {

	public Helper(int user_id, String username, String first_name, String last_name, String email, String birth_date) {
		super(user_id, username, first_name, last_name, email, birth_date);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getRole() {
		return "helper";
	}

}
