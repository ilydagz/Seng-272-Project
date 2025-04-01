public class Wish {
    private String wishId;
    private String title;
    private String description;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String status;
    private int level;

    public Wish(String wishId, String title, String description, String startDate, String startTime, 
                String endDate, String endTime, String status, int level) {
        this.wishId = wishId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.status = status;
        this.level = level;
    }

    // Getter ve Setter metodları
    public String getWishId() { return wishId; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }
}
