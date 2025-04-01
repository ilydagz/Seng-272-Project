public class Task {
    private int taskId;
    private String addedBy;
    private String title;
    private String description;
    private String deadlineDate;
    private String deadlineTime;
    private int points;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    public Task(int taskId, String addedBy, String title, String description, String deadlineDate, 
                String deadlineTime, int points, String startDate, String startTime, String endDate, String endTime) {
        this.taskId = taskId;
        this.addedBy = addedBy;
        this.title = title;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.points = points;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    // Getter ve Setter metodları
    public int getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public String getDeadlineDate() { return deadlineDate; }
}
