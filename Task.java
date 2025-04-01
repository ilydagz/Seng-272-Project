public class Task {
    private int id;
    private String title;
    private String description;
    private String deadline;
    private int points;
    private boolean isCompleted;
    private boolean isApproved;
    private int rating; // 1-5 yıldız üzerinden puanlama
    private DateRange activityTime; // Zaman aralığı gerektiren görevler için

    public Task(int id, String title, String description, String deadline, int points, DateRange activityTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.points = points;
        this.isCompleted = false;
        this.isApproved = false;
        this.rating = 0;
        this.activityTime = activityTime;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void approveTask(int rating) {
        if (isCompleted && rating >= 1 && rating <= 5) {
            this.isApproved = true;
            this.rating = rating;
        }
    }

    public boolean isTaskApproved() {
        return isApproved;
    }

    public int getPoints() {
        return points;
    }

    public int getRating() {
        return rating;
    }

    public DateRange getActivityTime() {
        return activityTime;
    }
}
