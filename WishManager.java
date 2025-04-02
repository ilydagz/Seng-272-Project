import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WishManager {
	private List<Wish> wishes;

	public WishManager() {
		wishes = new ArrayList<>();
	}

	public void addWishFromFile(String line) {
		// Çift tırnak içindeki kelimeleri düzgün ayırmak için REGEX kullanıyoruz
		String[] parts = line.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

		String wishId = parts[0];
		String title = parts[1].replace("\"", "");
		String description = parts[2].replace("\"", "");

		DateRange activityTime = null;
		if (parts.length == 8) {
			try {
				String startDate = parts[3];
				String startTime = parts[4];
				String endDate = parts[5];
				String endTime = parts[6];
				activityTime = new DateRange(startDate, startTime, endDate, endTime);
			} catch (Exception e) {
				System.out.println("Error processing wish: " + e.getMessage());
				return;
			}
		}

		Wish wish = new Wish(wishId, title, description, activityTime);
		wishes.add(wish);
		System.out.println("\nWish added: " + wish);
	}

	public List<Wish> getWishes() {
		return wishes;
	}

	public Wish findWishById(String wishId) {
		for (Wish wish : wishes) {
			if (wish.getId().equals(wishId)) {
				return wish;
			}
		}
		return null;
	}
}