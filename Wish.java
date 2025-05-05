public class Wish {
	private String wishId;
	private String title;
	private String description;
	private DateRange activityTime; // For WISH2
	private boolean isApproved;
	private int requiredLevel;

	public Wish(String wishId, String title, String description, DateRange activityTime) {
		this.wishId = wishId;
		this.title = title;
		this.description = description;
		this.activityTime = null; // Null for WISH1
		this.isApproved = false;
		this.requiredLevel = 0;
	}

	public Wish(String wishId, String title, String description, String startDate, String startTime, String endDate,
			String endTime) {
		this.wishId = wishId;
		this.title = title;
		this.description = description;
		this.activityTime = new DateRange(startDate, startTime, endDate, endTime); // Not null for WISH2 because it has activity time
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
		this.requiredLevel = level;
	}

	public void rejectWish() {
		this.isApproved = false;
	}

	@Override
	public String toString() {
	    return "\n========== WISH ==========\n" +
	           "Wish ID       : " + wishId + "\n" +
	           "Title         : " + title + "\n" +
	           "Description   : " + description + "\n" +
	           "Activity Time : " + (activityTime != null ? activityTime.toString() : "N/A") + "\n" +
	           "Approved      : " + (isApproved ? "Yes" : "No") + "\n" +
	           "Required Level: " + requiredLevel + "\n" +
	           "==========================\n";
	}

}
