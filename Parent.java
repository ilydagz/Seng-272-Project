public class Parent extends User {

    public Parent(String id, String name) {
        super(id, name, "Parent");
    }

    // Veli görev ekleyebilir
    public void addTask(Task task, TaskManager taskManager) {
        taskManager.addTask(task);
        System.out.println("Parent " + name + " added task: " + task);
    }

    // Veli görev onaylayabilir
    public void approveTask(Task task, int rating) {
        task.approveTask(rating);
    }

    // Veli bütçeye puan ekleyebilir
    public void addPointsToBudget(Budget budget, int points) {
        budget.addPoints(points);
    }

    // Veli dilekleri kabul edebilir veya reddedebilir
    public void approveOrRejectWish(Wish wish, String status, int level) {
        wishManager.approveOrRejectWish(wish.id, status, level);
    }
}
