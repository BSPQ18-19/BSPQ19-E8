package models;

public class User {

	private int user_id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String personal_id;
    private String birth_date;

    private Race[] runner_races;
    private Race[] organizer_races;
    private Race[] helper_races;

	public User(int user_id, String username, String first_name, String last_name, String email, String birth_date) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.birth_date = birth_date;

	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(String personal_id) {
		this.personal_id = personal_id;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public Race[] getRunner_races() {
		return runner_races;
	}

	public void setRunner_races(Race[] runner_races) {
		this.runner_races = runner_races;
	}

	public Race[] getOrganizer_races() {
		return organizer_races;
	}

	public void setOrganizer_races(Race[] organizer_races) {
		this.organizer_races = organizer_races;
	}

	public Race[] getHelper_races() {
		return helper_races;
	}

	public void setHelper_races(Race[] helper_races) {
		this.helper_races = helper_races;
	}

	public String getRole() {
		return "none";
	}


}
