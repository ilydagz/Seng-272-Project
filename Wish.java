public class Wish {
	private String wishId;
	private String title;
	private String description;
	private DateRange activityTime; // WISH2 için
	private boolean isApproved;
	private int requiredLevel;

	public Wish(String wishId, String title, String description, DateRange activityTime) {
		this.wishId = wishId;
		this.title = title;
		this.description = description;
		this.activityTime = null; // WISH1 için null
		this.isApproved = false;
		this.requiredLevel = 0;
	}

	public Wish(String wishId, String title, String description, String startDate, String startTime, String endDate,
			String endTime) {
		this.wishId = wishId;
		this.title = title;
		this.description = description;
		this.activityTime = new DateRange(startDate, startTime, endDate, endTime);
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
		return "Wish ID: " + wishId + ", Title: " + title + ", Description: " + description + ", Activity Time: "
				+ (activityTime != null ? activityTime : "N/A") + ", Approved: " + isApproved + ", Required Level: "
				+ requiredLevel;
	}
}
