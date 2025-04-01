import java.io.*;
import java.util.*;

public class Main {
	private static List<Task> tasks = new ArrayList<>();
	private static List<Wish> wishes = new ArrayList<>();
	private static List<Child> children = new ArrayList<>();

	// Method to process command files
	private static void processCommands(String fileName) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(fileName));
	        String line;
	        
	        while ((line = reader.readLine()) != null) {
	            // Komutları boşluklara göre ayır
	            String[] parts = line.split(" ");
	            
	            switch (parts[0]) {
	                case "ADD_TASK1":
	                case "ADD_TASK2":
	                    addTask(parts);
	                    break;
	                case "LIST_ALL_TASKS":
	                    listAllTasks(parts);
	                    break;
	                case "ADD_BUDGET_COIN":
	                    addBudgetCoin(parts);
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
	                case "ADD_WISH2":
	                    addWish(parts);
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
	                    System.out.println("Unknown command: " + parts[0]);
	            }
	        }
	        
	        reader.close();
	    } catch (IOException e) {
	        System.out.println("Error reading commands file: " + e.getMessage());
	    }
	}


	// Example methods for each command
	private static void addTask(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);
		String addedBy = parts[2]; // 'T' veya 'F'
		String title = parts[3].replaceAll("\"", "");
		String description = parts[4].replaceAll("\"", "");
		String deadlineDate = parts[5];
		String deadlineTime = parts[6];
		int points = Integer.parseInt(parts[7]);

		// Eğer start_date ve end_date varsa, bunları al
		String startDate = parts.length > 8 ? parts[8] : null;
		String startTime = parts.length > 9 ? parts[9] : null;
		String endDate = parts.length > 10 ? parts[10] : null;
		String endTime = parts.length > 11 ? parts[11] : null;

		// Yeni bir görev oluştur ve listeye ekle
		Task task = new Task(taskId, addedBy, title, description, deadlineDate, deadlineTime, points, startDate,
				startTime, endDate, endTime);
		tasks.add(task);
		System.out.println("Task added: " + title);
	}

	private static void listAllTasks(String[] parts) {
	    // Filtreleme tipi varsa, o türdeki görevleri göster
	    String filter = parts.length > 1 ? parts[1] : "";
	    
	    for (Task task : tasks) {
	        if (filter.isEmpty() || filter.equals("D") || filter.equals("W")) {
	            // Eğer günlük ya da haftalık filtre varsa, uygun şekilde işle
	            // Burada basit bir tarih kontrolü yapılabilir (örneğin, günlük veya haftalık filtreleme)
	            System.out.println("Task ID: " + task.getTaskId() + ", Title: " + task.getTitle() + ", Deadline: " + task.getDeadlineDate());
	        }
	    }
	}


	private static void addWish(String[] parts) {
		String wishId = parts[1];
		String title = parts[2].replaceAll("\"", "");
		String description = parts[3].replaceAll("\"", "");

		// Eğer start_date ve end_date varsa, bunları al
		String startDate = parts.length > 4 ? parts[4] : null;
		String startTime = parts.length > 5 ? parts[5] : null;
		String endDate = parts.length > 6 ? parts[6] : null;
		String endTime = parts.length > 7 ? parts[7] : null;

		String status = "PENDING"; // Başlangıçta dilek beklemede olacak
		int level = 1; // Varsayılan olarak level 1

		// Yeni bir dilek oluştur ve listeye ekle
		Wish wish = new Wish(wishId, title, description, startDate, startTime, endDate, endTime, status, level);
		wishes.add(wish);
		System.out.println("Wish added: " + title);
	}

	private static void taskDone(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);

		// Görevi tamamla (burada, örnek olarak sadece çıktıyı yazıyoruz)
		System.out.println("Task " + taskId + " marked as done.");
	}

	private static void taskChecked(String[] parts) {
		int taskId = Integer.parseInt(parts[1]);
		int rating = Integer.parseInt(parts[2]);

		// Görev tamamlandı ve onaylandı. Puanı ekle
		System.out.println("Task " + taskId + " approved with rating: " + rating + " stars.");
	}
	
	private static void listAllWishes() {
	    for (Wish wish : wishes) {
	        System.out.println("Wish ID: " + wish.getWishId() + ", Title: " + wish.getTitle() + ", Status: " + wish.getStatus());
	    }
	}


	private static void wishChecked(String[] parts) {
		String wishId = parts[1];
		String status = parts[2];
		int level = parts.length > 3 ? Integer.parseInt(parts[3]) : 1; // Level optional

		// Dileği onayla ya da reddet
		System.out.println("Wish " + wishId + " " + status + " with level: " + level);
	}
	
	private static void addBudgetCoin(String[] parts) {
	    int pointsToAdd = Integer.parseInt(parts[1]);
	    
	    // Burada örnek olarak tüm çocukların bütçesine ekliyoruz. İhtiyaca göre değiştirebiliriz.
	    for (Child child : children) {
	        child.addBudgetPoints(pointsToAdd);
	        System.out.println("Added " + pointsToAdd + " points to " + child.getName() + "'s budget.");
	    }
	}


	private static void printBudget() {
		for (Child child : children) {
			System.out.println(child.getName() + "'s current budget: " + child.getBudgetPoints() + " points.");
		}
	}

	private static void printStatus() {
		for (Child child : children) {
			System.out.println(child.getName() + "'s current status: " + "Level: " + child.getBudgetPoints());
		}
	}

	// Main method to execute the program
	public static void main(String[] args) {
		processCommands("Commands.txt");
	}
}
