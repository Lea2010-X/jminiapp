package com.jminiapp.examples.expense;

/**
 * Represents a single financial expense.
 */
public class ExpenseState {
    private int id;
    private String description;
    private double amount;
    private String category;

    // Default constructor required for JSON serialization
    public ExpenseState() {}

    public ExpenseState(int id, String description, double amount, String category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        // Format: [1] Lunch (Food): $15.50
        return String.format("[%d] %s (%s): $%.2f", id, description, category, amount);
    }
}
