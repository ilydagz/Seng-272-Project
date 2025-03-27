public class Task {
    private int id;
    private String title;
    private String description;
    private String deadline;
    private int points;

    public Task(int id, String title, String description, String deadline, int points) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.points = points;
    }

    public int getId() {
        return id;
    }
}
