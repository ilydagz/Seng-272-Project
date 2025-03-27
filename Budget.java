public class Budget {
	private int points;

	public Budget(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int amount) {
		points += amount;
	}
}
