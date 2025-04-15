public class BudgetManager {
	private int totalCoins;

	public BudgetManager() {
		this.totalCoins = 0;
	}

	// Add Coins
	public void addCoins(int amount) {
		this.totalCoins += amount;
	}

	// Gets the total coins
	public int getTotalCoins() {
		return totalCoins;
	}

	@Override
	public String toString() {
		return "Total Coins: " + totalCoins;
	}
}
