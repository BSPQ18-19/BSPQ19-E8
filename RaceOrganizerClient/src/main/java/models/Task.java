package models;

public class Task {
	
	private String description;
	private User person;
	private boolean completed;
	
	public Task(String description, User person, boolean completed) {
		this.description = description;
		this.person = person;
		this.completed = completed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return person.getUsername();
	}
	public void setPerson(User person) {
		this.person = person;
	}
	public User getPerson() {
		return this.person;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	@Override
	public String toString() {
		return "Task [description=" + description + ", username=" + person.getUsername() + ", completed=" + completed + "]";
	}
}
