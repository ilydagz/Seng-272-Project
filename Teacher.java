public class Teacher extends User {

    public Teacher(String id, String name) {
        super(id, name, "Teacher");
    }

    // Öğretmen görev ekleyebilir
    public void addTask(Task task, TaskManager taskManager) {
        taskManager.addTask(task);
        System.out.println("Teacher " + name + " added task: " + task);
    }

    // Öğretmen görev onaylayabilir
    public void approveTask(Task task, int rating) {
        task.approveTask(rating);
    }

    

    // Öğretmen bütçeye puan ekleyebilir
    public void addPointsToBudget(Budget budget, int points) {
        budget.addPoints(points);
    }
}

