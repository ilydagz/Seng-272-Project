import java.time.format.DateTimeFormatter;

public class Wish {
	private String wishId;
	private String title;
	private String description;
	private DateRange activityTime; // For WISH2
	private String status; 
	private boolean isApproved;
	private int requiredLevel;

	public Wish(String wishId, String title, String description, DateRange activityTime) {
		this.wishId = wishId;
		this.title = title;
		this.description = description;
		this.activityTime = null; // Null for WISH1
		this.status = "PENDING";
		this.isApproved = false;
		this.requiredLevel = 0;
	}

	public Wish(String wishId, String title, String description, String startDate, String startTime, String endDate,
			String endTime) {
		this.wishId = wishId;
		this.title = title;
		this.description = description;
		this.activityTime = new DateRange(startDate, startTime, endDate, endTime); // Not null for WISH2 because it has activity time
		this.status = "PENDING";
		this.isApproved = false;
		this.requiredLevel = 0;
	}

	public String getId() {
		return wishId;
	}

	public String getTitle() {
		return title;
	}

	public void approveWish(int level) {
		this.isApproved = true;
		this.status = "APPROVED";
		this.requiredLevel = level;
	}

	public void rejectWish() {
		this.isApproved = false;
		this.status = "REJECTED";
	}

	@Override
	public String toString() {
	    return "\n========== WISH ==========\n" +
	           "Wish ID       : " + wishId + "\n" +
	           "Title         : " + title + "\n" +
	           "Description   : " + description + "\n" +
	           "Status        : "  + status + "\n" +
	           "Activity Time : " + (activityTime != null ? activityTime.toString() : "N/A") + "\n" +
	           "Approved      : " + (isApproved ? "Yes" : "No") + "\n" +
	           "Required Level: " + requiredLevel + "\n" +
	           "==========================\n";
	}
	
	public String toFileString() {
	    if (activityTime == null) {
	        // WISH1
	        return String.format("\n%s \"%s\" \"%s\" %s",
	                wishId,
	                title,
	                description,
	                status);
	    } else {
	        // WISH2
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	        return String.format("\n%s \"%s\" \"%s\" %s %s %s %s %s",
	                wishId,
	                title,
	                description,
	                activityTime.getStart().toLocalDate().format(dateFormatter),
	                activityTime.getStart().toLocalTime().format(timeFormatter),
	                activityTime.getEnd().toLocalDate().format(dateFormatter),
	                activityTime.getEnd().toLocalTime().format(timeFormatter),
	                status);
	    }
	}


}
