import java.util.ArrayList;
import java.util.List;

public class Child extends User {
    private List<Task> tasks;
    private Budget budget;
    private int level;

    public Child(String name) {
        super(name);
        this.tasks = new ArrayList<>();
        this.budget = new Budget(0);
        this.level = 1;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.markAsCompleted();
                break;
            }
        }
    }

    public void approveTask(int taskId, int rating) {
        for (Task task : tasks) {
            if (task.getId() == taskId && task.isTaskApproved() == false) {
                task.approveTask(rating);
                budget.addPoints(task.getPoints());
                updateLevel();
                break;
            }
        }
    }

    private void updateLevel() {
        int avgPoints = budget.getPoints();
        if (avgPoints >= 80) level = 4;
        else if (avgPoints >= 60) level = 3;
        else if (avgPoints >= 40) level = 2;
        else level = 1;
    }

    public int getLevel() {
        return level;
    }
}
