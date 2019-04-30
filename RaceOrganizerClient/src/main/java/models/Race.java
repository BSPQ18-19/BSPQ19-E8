package models;

import java.util.Date;

public class Race {
	
	private int race_id;
	private String edition;
	private String sponsor;
	private String place;
	private Date time;
	private float price;
	private float prize;
	
	private User[] runners;
	private User[] helpers;
	
	public Race(int race_id, String edition, String sponsor, String place, Date time, float price, float prize,
			User[] runners, User[] helpers) {
		super();
		this.race_id = race_id;
		this.edition = edition;
		this.sponsor = sponsor;
		this.place = place;
		this.time = time;
		this.price = price;
		this.prize = prize;
		this.runners = runners;
		this.helpers = helpers;
	}

	public int getRace_id() {
		return race_id;
	}

	public void setRace_id(int race_id) {
		this.race_id = race_id;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrize() {
		return prize;
	}

	public void setPrize(float prize) {
		this.prize = prize;
	}

	public User[] getRunners() {
		return runners;
	}

	public void setRunners(User[] runners) {
		this.runners = runners;
	}

	public User[] getHelpers() {
		return helpers;
	}

	public void setHelpers(User[] helpers) {
		this.helpers = helpers;
	}
	
	

}
