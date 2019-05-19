package models;

public class Task {

    private int task_id;
    private String description;
    private User person;
    private boolean completed;

    public Task(int task_id, String description, User person, boolean completed) {
        this.task_id = task_id;
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

    public User getPerson() {
        return this.person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    @Override
    public String toString() {
        return "Task [description=" + description + ", username=" + person.getUsername() + ", completed=" + completed + "]";
    }
}
