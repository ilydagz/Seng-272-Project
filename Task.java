import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
	private String type;
	private int taskId;
	private String title;
	private String description;
	public LocalDateTime deadline;
	private int points;
	public DateRange activityTime; // For TASK2

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
		this.activityTime = null; // Leave it null for TASK1 because it has deadline
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
		this.deadline = null; // No deadline for TASK2 because it has activity time
		this.points = points;
		this.activityTime = new DateRange(startDate, startTime, endDate, endTime);
		this.completed = false;
		this.isApproved = false;
		this.rating = 0;
	}

	public int getId() {
		return taskId;
	}

	public String getType() {
		return type;
	}

	public void markAsCompleted() {
		this.completed = true;
	}

	public boolean isCompleted() {
		return completed;
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
	    return "\n=========== TASK ===========\n" +
	           "Type       : " + type + "\n" +
	           "Task ID    : " + taskId + "\n" +
	           "Title      : " + title + "\n" +
	           "Description: " + description + "\n" +
	           "Deadline   : " +
	           (deadline != null ? deadline.toString().replace("T", " ")
	                             : (activityTime != null ? activityTime.toString() : "N/A")) + "\n" +
	           "Points     : " + points + "\n" +
	           "Completed  : " + (completed ? "Yes" : "No") + "\n" +
	           "Approved   : " + (isApproved ? "Yes" : "No") + "\n" +
	           "Rating     : " + rating + "\n" +
	           "============================\n";
	}
	
}
