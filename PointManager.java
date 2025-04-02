public class PointManager {
	private int points;

	public PointManager() {
		this.points = 0;
	}

	public void addPoints(int amount) {
		if (amount > 0) {
			points += amount;
			System.out.println(amount + " points added. Total points: " + points);
		} else {
			System.out.println("Invalid points amount.");
		}
	}

	public int getPoints() {
		return points;
	}

	public void resetPoints() {
		points = 0;
		System.out.println("Points reset to zero.");
	}
}