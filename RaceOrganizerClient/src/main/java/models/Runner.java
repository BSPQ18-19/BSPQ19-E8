package models;


public class Runner extends User {

	private int number;
	
	public Runner(int user_id, String username, String first_name, String last_name, String email, String birth_date, int number) {
		super(user_id, username, first_name, last_name, email, birth_date);
		// TODO Auto-generated constructor stub
	}
	
	public Runner(User u, int number) {
		super(u.getUser_id(), u.getUsername(), u.getFirst_name(), u.getLast_name(), u.getEmail(), u.getBirth_date());
		this.number = number;
	}
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String getRole() {
		return "runner";
	}

}
