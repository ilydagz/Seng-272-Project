public class Parent {
	private String name;
	private Child child;

	public Parent(String name, Child child) {
		this.name = name;
		this.child = child;
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
			child.updatePoints(task.getPoints());
			child.updateRating(rating);
		}
	}

	public void addBudgetCoins(BudgetManager budgetManager, int amount) {
		budgetManager.addCoins(amount);
	}

	public void checkWish(Wish wish, boolean approve, int level) {
		if (approve) {
			wish.approveWish(level);
		} else {
			wish.rejectWish();
		}
	}

	@Override
	public String toString() {
		return "Parent: " + name;
	}
}