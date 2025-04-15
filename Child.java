import java.util.ArrayList;
import java.util.List;

public class Child {
   
    private List<Task> tasks;
    private List<Wish> wishes;
    private int points = 0;
    private double averageRating;
    private int level=0;
    private BudgetManager budgetManager;

    public Child(BudgetManager budgetManager) {
        this.tasks = new ArrayList<>();
        this.wishes = new ArrayList<>();
        this.points = 0;
        this.averageRating = 0.0;
        this.level=0;
        this.budgetManager = budgetManager;
    }

    public void completeTask(Task task) {
        task.markAsCompleted(); // Mark the task as completed
        System.out.println("Task " + task.getTitle() + " marked as completed.");
    }


    public int getPoints() {
        return points;
        
    }

    public void addPoints(int points) {
        this.points += points;
        updateLevel();
    }

    public void updateRating(int rating) {
        this.averageRating = (this.averageRating + rating) / 2.0;
    }
    
    public void updateLevel() {
        if (points < 40) level = 1;
        else if (points < 60) level = 2;
        else if (points < 80) level = 3;
        else level = 4;
    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addWish(Wish wish) {
        wishes.add(wish);
    }
    // Gets budget
    public int getBudget() {
        return budgetManager.getTotalCoins(); // Gets the budget from budgetManager class
    }
    public int getLevel() {
    	return level;
    }


    @Override
    public String toString() {
        return "Child's Total Points: " + points + ", Level: " + level;
    }
}
