import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		TaskManager taskManager = new TaskManager();
		WishManager wishManager = new WishManager();

		CommandProcessor commandProcessor = new CommandProcessor(taskManager, wishManager);

		readTasksFromFile("Tasks.txt", taskManager);

		readWishesFromFile("Wishes.txt", wishManager);

		processCommandsFromFile("Commands.txt", commandProcessor);
	}

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

	private static void processCommandsFromFile(String fileName, CommandProcessor commandProcessor) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String command = scanner.nextLine();
				commandProcessor.processCommand(command);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		}
	}
}
