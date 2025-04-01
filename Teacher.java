public class Teacher extends User {
    private TaskManager taskManager;

    public Teacher(String name) {
        super(name);
        this.taskManager = new TaskManager();
    }

    public void assignTask(Child child, Task task) {
        taskManager.addTaskToChild(child, task);
    }

    public void approveChildTask(Child child, int taskId, int rating) {
        taskManager.approveTask(child, taskId, rating);
    }
}

