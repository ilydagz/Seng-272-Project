public class Child {
    private String name;
    private int budgetPoints;

    // Constructor
    public Child(String name, int budgetPoints) {
        this.name = name;
        this.budgetPoints = budgetPoints;
    }

    // Getters and setters
    public String getName() { return name; }
    public int getBudgetPoints() { return budgetPoints; }
    public void addPoints(int points) { this.budgetPoints += points; }
}
