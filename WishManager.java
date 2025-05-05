import java.util.ArrayList;
import java.util.List;

public class WishManager {
	private List<Wish> wishes;

	public WishManager() {
		wishes = new ArrayList<>();
	}

	// Processes the information coming from Wishes.txt file and then adds wish to
	// wishes
	public void addWishFromFile(String line) {

		String[] parts = line.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

		// We use REGEX for splitting the parts inside the double quotes

		String wishId = parts[0];
		String title = parts[1];
		String description = parts[2];

		DateRange activityTime = null;
		Wish wish;
		if (parts.length >= 7) {

			String startDate = parts[3];
			String startTime = parts[4];
			String endDate = parts[5];
			String endTime = parts[6];
			activityTime = new DateRange(startDate, startTime, endDate, endTime);
			wish = new Wish(wishId, title, description, startDate, startTime, endDate, endTime);
		}

		else {
			wish = new Wish(wishId, title, description, activityTime);
		}
		wishes.add(wish);
		System.out.println("----------------------------------------"); 
		System.out.println("\nWish created:\n\n" + wish);
	}

	public List<Wish> getWishes() {
		return wishes;
	}

	public void removeWish(Wish wish) {
		wishes.remove(wish);
	}

	public Wish findWishById(String wishId) {
		for (Wish wish : wishes) {
			if (wish.getId().equals(wishId)) {
				return wish;
			}
		}
		System.out.println("Wish with ID " + wishId + " not found.\n");
		return null;
	}
}