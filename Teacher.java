public class Teacher {
	private String name;

	public Teacher(String name) {
		this.name = name;
	}

	public Task createTask1(String type, int taskId, String title, String description, String deadlineDate,
			String deadlineTime, int points) {
		return new Task(type, taskId, title, description, deadlineDate, deadlineTime, points);
	}

	public Task createTask2(String type, int taskId, String title, String description, String startDate,
			String startTime, String endDate, String endTime, int points) {
		return new Task(type, taskId, title, description, startDate, startTime, endDate, endTime, points);
	}

	public void approveTask(Task task, int rating) {
		if (task != null && task.isCompleted()) {
			task.approveTask(rating);
		}
	}

	@Override
	public String toString() {
		return "Teacher: " + name;
	}
}
