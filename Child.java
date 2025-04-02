import java.util.ArrayList;
import java.util.List;

public class Child {
	private String name;
	private List<Task> tasks;
	private List<Wish> wishes;
	private int points;
	private double averageRating;

	public Child(String name) {
		this.name = name;
		this.tasks = new ArrayList<>();
		this.wishes = new ArrayList<>();
		this.points = 0;
		this.averageRating = 0.0;
	}

	public void completeTask(Task tasks) {
		tasks.isCompleted();
		System.out.println("Task " + tasks + " marked as completed.");
	}

	public int getPoints() {
		return points;
	}

	public void updatePoints(int points) {
		this.points += points;
	}

	public void updateRating(int rating) {
		this.averageRating = (this.averageRating + rating) / 2.0;
	}

	public int getLevel() {
		if (averageRating < 40)
			return 1;
		if (averageRating < 60)
			return 2;
		if (averageRating < 80)
			return 3;
		return 4;
	}

	@Override
	public String toString() {
		return "Child: " + name + ", Total Points: " + points + ", Level: " + getLevel();
	}
}
