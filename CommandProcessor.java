import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {
	private Child child;
	private Parent parent;
	private Teacher teacher;
	private BudgetManager budgetManager;
	private List<Task> tasks;
	private List<Wish> wishes;
	private TaskManager taskManager;
	private WishManager wishManager;

	// Constructor ekliyoruz
	public CommandProcessor(TaskManager taskManager, WishManager wishManager) {
		this.taskManager = taskManager;
		this.wishManager = wishManager;
	}

	public CommandProcessor(Child child, Parent parent, Teacher teacher, BudgetManager budgetManager) {
		this.child = child;
		this.parent = parent;
		this.teacher = teacher;
		this.budgetManager = budgetManager;
		this.tasks = new ArrayList<>();
		this.wishes = new ArrayList<>();
	}

	// Komutları işlemek için ana metod
	public void processCommand(String commandLine) {
		String[] parts = commandLine.split(" ");
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

	// TASK ekleme
	private void addTask(String[] parts) {
		String type = parts[1];
		int taskId = Integer.parseInt(parts[2]);
		String title = parts[3];
		String description = parts[4];
		String deadlineDate = parts[5];
		String deadlineTime = parts[6];
		int points = Integer.parseInt(parts[7]);

		Task task;
		if (parts.length == 10) {
			// ADD_TASK2 formatı, başlangıç ve bitiş tarihi eklenmiş
			String startDate = parts[5];
			String startTime = parts[6];
			String endDate = parts[7];
			String endTime = parts[8];
			points = Integer.parseInt(parts[9]);
			task = new Task(type, taskId, title, description, startDate, startTime, endDate, endTime, points);
		}
		task = new Task(type, taskId, title, description, deadlineDate, deadlineTime, points);

		tasks.add(task);
		if (type.equals("T")) {
			teacher.approveTask(task, 0); // Öğretmen task eklerken onaylama yapabilir
		} else if (type.equals("P")) {
			parent.approveTask(task, 0); // Aile task eklerken onaylama yapabilir
		}
		System.out.println("Task added: " + task);
	}

	// Görevleri listeleme
	private void listAllTasks(String[] parts) {
		String filter = parts.length > 1 ? parts[1] : "";

		System.out.println("Listing tasks:");
		for (Task task : tasks) {
			if (filter.equals("D")) {
				// Günlük filtreleme (bu kısım basitçe tüm görevleri listeler)
				System.out.println(task);
			} else if (filter.equals("W")) {
				// Haftalık filtreleme (bu kısım basitçe tüm görevleri listeler)
				System.out.println(task);
			} else {
				System.out.println(task);
			}
		}
	}

	// Tüm dilekleri listeleme
	private void listAllWishes() {
		System.out.println("Listing all wishes:");
		for (Wish wish : wishes) {
			System.out.println(wish);
		}
	}

	// Görev tamamlandı
	private void taskDone(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);
		Task task = findTaskById(taskId);
		if (task != null) {
			task.markAsCompleted();
			System.out.println("Task " + taskId + " marked as done.");
		}
	}

	// Görev onaylama
	private void taskChecked(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);
		int rating = Integer.parseInt(parts[2]);
		Task task = findTaskById(taskId);
		if (task != null) {
			if (parent != null) {
				parent.approveTask(task, rating);
			} else if (teacher != null) {
				teacher.approveTask(task, rating);
			}
			System.out.println("Task " + taskId + " approved with rating " + rating);
		}
	}

	// Dilek ekleme
	private void addWish(String[] parts) {
		String wishId = parts[0];
		String title = parts[1];
		String description = parts[2];
		DateRange activityTime = null;
		if ((parts.length == 8)) {
			String startDate = parts[3];
			String startTime = parts[4];
			String endDate = parts[5];
			String endTime = parts[6];
			activityTime = new DateRange(startDate, startTime, endDate, endTime); // Opsiyonel field
		}

		Wish wish = new Wish(wishId, title, description, activityTime);
		wishes.add(wish);
		System.out.println("Wish added: " + wish);
	}

	// Bütçe coin ekleme
	private void addBudgetCoin(String[] parts) {
		int amount = Integer.parseInt(parts[1]);
		budgetManager.addCoins(amount); // BudgetManager üzerinden coin ekleme
		System.out.println(amount + " coins added to child's budget.");
	}

	// Dilek onaylama
	private void wishChecked(String[] parts) {
		String wishId = parts[1];
		String status = parts[2];
		int level = parts.length > 3 ? Integer.parseInt(parts[3]) : 0;

		Wish wish = findWishById(wishId);
		if (wish != null) {
			if (status.equals("APPROVED")) {
				parent.checkWish(wish, true, level);
				System.out.println("Wish " + wishId + " approved.");
			} else if (status.equals("REJECTED")) {
				parent.checkWish(wish, false, level);
				System.out.println("Wish " + wishId + " rejected.");
			}
		}
	}

	// Bütçeyi yazdırma
	private void printBudget() {
		System.out.println("Child's current budget: " + child.getPoints() + " points.");
	}

	// Çocuğun durumunu yazdırma
	private void printStatus() {
		System.out.println("Child's current status: " + child);
	}

	// ID ile görev bulma
	private Task findTaskById(int taskId) {
		if (tasks == null || tasks.isEmpty()) {
			System.out.println("No tasks available.");
			return null;
		}
		for (Task task : tasks) {
			if (task.getId() == taskId) {
				return task;
			}

		}
		System.out.println("Task with ID " + taskId + " not found.");
		return null;
	}

	// ID ile dilek bulma
	private Wish findWishById(String wishId) {
		for (Wish wish : wishes) {
			if (wish.getId() == wishId) {
				return wish;
			}

		}
		System.out.println("Wish with ID " + wishId + " not found.");
		return null;
	}
}