public class BudgetManager {
	private int totalCoins;

	public BudgetManager() {
		this.totalCoins = 0;
	}

	// Coins/puan ekler
	public void addCoins(int amount) {
		this.totalCoins += amount;
	}

	// Mevcut coin sayısını alır
	public int getTotalCoins() {
		return totalCoins;
	}

	@Override
	public String toString() {
		return "Total Coins: " + totalCoins;
	}
}
