import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
	private Child child;
	private Parent parent;
	private Teacher teacher;
	private BudgetManager budgetManager;
	private List<Task> tasks;
	private List<Wish> wishes;
	private TaskManager taskManager;
	private WishManager wishManager;

	// Constructor

	public CommandProcessor(Child child, Parent parent, Teacher teacher, BudgetManager budgetManager,
			TaskManager taskManager, WishManager wishManager) {
		this.child = child;
		this.parent = parent;
		this.teacher = teacher;
		this.budgetManager = budgetManager;
		this.taskManager = taskManager;
		this.wishManager = wishManager;
		this.tasks = new ArrayList<>();
		this.wishes = new ArrayList<>();
	}

	public static String[] splitCommandLine(String line) {
		line = line.trim(); // Clean up the spaces at the beginning and at the end
		List<String> partsList = new ArrayList<>();
		Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
		Matcher matcher = pattern.matcher(line);

		while (matcher.find()) {
			if (matcher.group(1) != null) {
				partsList.add(matcher.group(1)); // Take inside double quotes
			} else {
				partsList.add(matcher.group(2)); // Take the normal words
			}
		}

		return partsList.toArray(new String[0]);
	}

	// Main method to process commands
	public void processCommand(String commandLine) {
		String[] parts = splitCommandLine(commandLine);
		String command = parts[0];

		switch (command) {
		case "ADD_TASK1":
			addTask(parts);
			break;
		case "ADD_TASK2":
			addTask(parts);
			break;
		case "LIST_ALL_TASKS":
			listAllTasks(parts);
			break;
		case "LIST_ALL_WISHES":
			listAllWishes();
			break;
		case "TASK_DONE":
			taskDone(parts);
			break;
		case "TASK_CHECKED":
			taskChecked(parts);
			break;
		case "ADD_WISH1":
			addWish(parts);
			break;
		case "ADD_WISH2":
			addWish(parts);
			break;
		case "ADD_BUDGET_COIN":
			addBudgetCoin(parts);
			break;
		case "WISH_CHECKED":
			wishChecked(parts);
			break;
		case "PRINT_BUDGET":
			printBudget();
			break;
		case "PRINT_STATUS":
			printStatus();
			break;
		default:
			System.out.println("Invalid command.");
		}
	}

	// Adding task
	private void addTask(String[] parts) {
		if (parts.length < 8) {
			System.out.println("Not enough task parameters.");
			return;
		}

		String type = parts[1];
		int taskId = Integer.parseInt(parts[2]);
		String title = parts[3];
		String description = parts[4];

		Task task;

		if (parts.length == 10) {
			// ADD_TASK2 (task with activity time)
			String startDate = parts[5];
			String startTime = parts[6];
			String endDate = parts[7];
			String endTime = parts[8];
			int points = Integer.parseInt(parts[9]);

			task = new Task(type, taskId, title, description, startDate, startTime, endDate, endTime, points);

		} else if (parts.length == 8) {
			// ADD_TASK1
			String deadlineDate = parts[5];
			String deadlineTime = parts[6];
			int points = Integer.parseInt(parts[7]);

			task = new Task(type, taskId, title, description, deadlineDate, deadlineTime, points);

		} else {
			System.out.println("ınvalid task command format. Number of parts: " + parts.length);
			return;
		}

		tasks.add(task);
		System.out.println("----------------------------------------");
		System.out.println("\nTask added:\n" + task + "\n");
	}

	// Listing tasks
	private void listAllTasks(String[] parts) {
		String filter = parts.length > 1 ? parts[1].toUpperCase() : "ALL";
		System.out.println("----------------------------------------");

		// Creating a title because of the filtering and printing it accordingly
		String title = "Listing all tasks:\n"; // Default title
		if (filter.equals("D")) {
			title = "Listing daily tasks:\n";
		} else if (filter.equals("W")) {
			title = "Listing weekly tasks:\n";
		}

		System.out.println(title); // Dynamic title starts here
		System.out.println("----------------------------------------");

		for (Task task : taskManager.getTasks()) {
			boolean isTask1 = (task.deadline != null && task.activityTime == null); // TASK1 - daily
			boolean isTask2 = (task.activityTime != null && task.deadline == null); // TASK2 - weekly

			switch (filter) {
			case "D": // Daily
				if (isTask1) {
					System.out.println(task + "\n");
				}
				break;
			case "W": // Weekly
				if (isTask2) {
					System.out.println(task + "\n");
				}
				break;
			case "ALL":
			default:
				System.out.println(task + "\n");
			}
		}
		System.out.println("----------------------------------------");
	}

	// Listing wishes
	private void listAllWishes() {
		System.out.println("----------------------------------------");
		System.out.println("Listing all wishes:\n");
		System.out.println("----------------------------------------");
		for (Wish wish : wishManager.getWishes()) {
			System.out.println(wish + "\n");
		}
		System.out.println("----------------------------------------");
	}

	// Task done
	private void taskDone(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);
		Task task = taskManager.findTaskById(taskId);
		if (task != null) {
			task.markAsCompleted();
			System.out.println("Task " + taskId + " marked as done.\n");
		}
	}

	// Checks a task then approves with rating
	private void taskChecked(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);
		int rating = Integer.parseInt(parts[2]);
		Task task = taskManager.findTaskById(taskId);
		if (task != null) {
			if (parent != null) {
				parent.approveTask(task, rating);
			} else if (teacher != null) {
				teacher.approveTask(task, rating);
			}

			System.out.println("Task " + taskId + " approved with rating " + rating + "\n");

		}
	}

	// Adding a wish
	private void addWish(String[] parts) {
		if (parts.length < 4) {
			System.out.println("Not enough wish parameters.");
			return;
		}

		String wishId = parts[1];
		String title = parts[2];
		String description = parts[3];

		DateRange activityTime = null;
		Wish wish;

		if (parts.length >= 8) {
			try {
				// ADD_WISH2 (wish with activity time)
				String startDate = parts[4];
				String startTime = parts[5];
				String endDate = parts[6];
				String endTime = parts[7];
				activityTime = new DateRange(startDate, startTime, endDate, endTime);
				wish = new Wish(wishId, title, description, startDate, startTime, endDate, endTime);
			} catch (Exception e) {
				System.out.println("Error: Can not process wish date/time range -> " + e.getMessage());
				return;
			}
		}

		else {
			// ADD_WISH1
			wish = new Wish(wishId, title, description, activityTime);
		}
		wishes.add(wish);
		System.out.println("----------------------------------------");
		System.out.println("Wish added:\n\n" + wish + "\n");
	}

	// Adds coins to the budget
	private void addBudgetCoin(String[] parts) {
		int amount = Integer.parseInt(parts[1]);
		budgetManager.addCoins(amount); // Coin addition from budgetManager class
		System.out.println(amount + " coins added to child's budget.\n");
	}

	// Checks a wish then approves
	private void wishChecked(String[] parts) {
		String wishId = parts[1];
		String status = parts[2];
		int level = parts.length > 3 ? Integer.parseInt(parts[3]) : 0;

		// Take the method from wishManager:
		Wish wish = wishManager.findWishById(wishId);
		if (wish != null) {
			if (status.equals("APPROVED")) {
				parent.checkWish(wish, true, level);
				System.out.println("Wish " + wishId + " approved. Required level: " + level + "\n");
			} else if (status.equals("REJECTED")) {
				parent.checkWish(wish, false, level);
				wishManager.removeWish(wish);
				System.out.println("Wish " + wishId + " rejected.\n");
			}
		} else {
			System.out.println("Wish with ID " + wishId + " not found.\n");
		}
	}

	// Printing the budget
	private void printBudget() {
		if (child != null) {
			System.out.println("Child's current budget: " + child.getBudget() + " coins.\n");
		} else {
			System.out.println("Child is not initialized.\n");
		}
	}

	// Prints the status of child (child's level)
	private void printStatus() {
		System.out.println("Child's current status: " + child.getLevel());
	}

}