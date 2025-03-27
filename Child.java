import java.util.ArrayList;
import java.util.List;

public class Child extends User {
	private List<Task> tasks;
	private Budget budget;

	public Child(String name) {
		super(name);
		this.tasks = new ArrayList<>();
		this.budget = new Budget(0);
	}

	public Budget getBudget() {
		return budget;
	}

	public List<Task> getTasks() {
		return tasks;
	}
}
