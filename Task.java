import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
	private String type;
	private int taskId;
	private String title;
	private String description;
	private LocalDateTime deadline;
	private int points;
	private DateRange activityTime; // TASK2 için

	private boolean completed;
	private boolean isApproved;
	private int rating;

	public Task(String type, int taskId, String title, String description, String deadlineDate, String deadlineTime,
			int points) {
		this.type = type;
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.deadline = LocalDateTime.parse(deadlineDate + " " + deadlineTime,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.points = points;
		this.activityTime = null; // TASK1 için null bırakıyoruz
		this.completed = false;
		this.isApproved = false;
		this.rating = 0;
	}

	public Task(String type, int taskId, String title, String description, String startDate, String startTime,
			String endDate, String endTime, int points) {
		this.type = type;
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.deadline = null; // TASK2 için deadline yok
		this.points = points;
		this.activityTime = new DateRange(startDate, startTime, endDate, endTime);
		this.completed = false;
		this.isApproved = false;
		this.rating = 0;
	}

	public int getID(int taskId) {
		return taskId;
	}

	public String getType(String type) {
		return type;
	}

	public void markAsCompleted() {
		this.completed = true;
	}

	public boolean isCompleted() {
		return completed;
	}

	public int getId() {
		return taskId;
	}

	public String getTitle() {
		return title;
	}

	public void approveTask(int rating) {
		if (completed) {
			this.isApproved = true;
			this.rating = rating;
		}
	}

	public int getPoints() {
		return isApproved ? points : 0;
	}

	@Override
	public String toString() {
		return "Type: " + type + ", Task ID: " + taskId + ", Title: " + title + ", Description: " + description
				+ ", Deadline: " + (deadline != null ? deadline : activityTime) + ", Points: " + points
				+ ", Completed: " + completed + ", Approved: " + isApproved + ", Rating: " + rating;
	}
}