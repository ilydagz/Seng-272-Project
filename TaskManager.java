import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
	private List<Task> tasks;

	public TaskManager() {
		tasks = new ArrayList<>();
	}

	public static String[] splitCommandLine(String line) {
		List<String> partsList = new ArrayList<>();
		Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
		Matcher matcher = pattern.matcher(line);

		while (matcher.find()) {
			if (matcher.group(1) != null) {
				partsList.add(matcher.group(1)); // Gets the inside double quotes
			} else {
				partsList.add(matcher.group(2)); // Take the normal words
			}
		}

		return partsList.toArray(new String[0]);
	}

	// Processes the information coming from Tasks.txt file then adds the task to
	// tasks
	public void addTaskFromFile(String line) {

		String[] parts = splitCommandLine(line);

		try {
			String type = parts[0]; // "T" or "P"
			int taskId = Integer.parseInt(parts[1]);
			String title = parts[2];
			String description = parts[3];

			Task task;
			if (parts.length == 10) { // For tasks that has activity time
				String startDate = parts[4];
				String startTime = parts[5];
				String endDate = parts[6];
				String endTime = parts[7];
				int points = Integer.parseInt(parts[8]);

				System.out.println("\nCreating Task with activity time.");
				task = new Task(type, taskId, title, description, startDate, startTime, endDate, endTime, points);
			} else if (parts.length == 8) { // For tasks that has deadline
				String deadlineDate = parts[4];
				String deadlineTime = parts[5];
				int points = Integer.parseInt(parts[6]);

				System.out.println("\nCreating Task with deadline.");
				task = new Task(type, taskId, title, description, deadlineDate, deadlineTime, points);
			} else {
				throw new IllegalArgumentException("Unexpected number of parameters in task data.");
			}

			tasks.add(task);
			
			
			System.out.println("----------------------------------------"); 
			System.out.println("\nTask created:\n\n" + task);
		} catch (Exception e) {
			System.out.println("Error processing task: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public List<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}

	public Task findTaskById(int taskId) {
		if (tasks == null || tasks.isEmpty()) {
			System.out.println("No tasks available.\n");
			return null;
		}
		for (Task task : tasks) {
			if (task.getId() == taskId) {
				return task;
			}
		}
		System.out.println("Task with ID " + taskId + " not found.\n");
		return null;
	}
}