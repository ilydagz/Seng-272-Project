import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Creating budgetManager, child, parent, teacher, taskManager and wishManager objects
		BudgetManager budgetManager = new BudgetManager();
		Child child = new Child(budgetManager);
		Parent parent = new Parent(child);
		Teacher teacher = new Teacher();

		TaskManager taskManager = new TaskManager();
		WishManager wishManager = new WishManager();

		// Creating commandProcessor object
		CommandProcessor commandProcessor = new CommandProcessor(child, parent, teacher, budgetManager, taskManager,
				wishManager);

		// Reads Tasks.txt file and adds this to taskManager 
		readTasksFromFile("Tasks.txt", taskManager);

		// Reads Wishes.txt file and adds this to wishManager
		readWishesFromFile("Wishes.txt", wishManager);

		// Reads Commands.txt file and process the commands
		processCommandsFromFile("Commands.txt", commandProcessor);
	}

	// TReading from Tasks.txt
	private static void readTasksFromFile(String fileName, TaskManager taskManager) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				taskManager.addTaskFromFile(line);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		}
	}

	// Reading from Wishes.txt
	private static void readWishesFromFile(String fileName, WishManager wishManager) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				wishManager.addWishFromFile(line);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		}
	}

	// Reading from Commands.txt
	private static void processCommandsFromFile(String fileName, CommandProcessor commandProcessor) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (!line.isEmpty()) {
					commandProcessor.processCommand(line); // BU SATIR ZATEN splitCommandLine KULLANIYOR
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Command file not found: " + e.getMessage());
		}
	}

}

