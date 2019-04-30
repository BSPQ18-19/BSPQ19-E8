package models;

public class Organizer extends User {

	public Organizer(int user_id, String username, String first_name, String last_name, String email,
			String birth_date) {
		super(user_id, username, first_name, last_name, email, birth_date);
	
	
	}
	@Override
	public String getRole() {
		return "organizer";
	}
}
