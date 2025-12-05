package com.jminiapp.examples.expense;

import com.jminiapp.core.api.JMiniApp;
import com.jminiapp.core.api.JMiniAppConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseApp extends JMiniApp {
    private Scanner scanner;
    private List<ExpenseState> expenses;
    private boolean running;

    public ExpenseApp(JMiniAppConfig config) {
        super(config);
    }

    @Override
    protected void initialize() {
        System.out.println("\n=== Personal Expense Tracker ===");
        scanner = new Scanner(System.in);
        expenses = new ArrayList<>();
        running = true;

        // Load previous data
        List<ExpenseState> data = context.getData();
        if (data != null && !data.isEmpty()) {
            expenses.addAll(data);
            System.out.println("Loaded " + expenses.size() + " records.");
        }
    }

    @Override
    protected void run() {
        while (running) {
            displayMenu();
            handleInput();
        }
    }

    @Override
    protected void shutdown() {
        context.setData(expenses);
        scanner.close();
        System.out.println("Saving data... Goodbye!");
    }

    private void displayMenu() {
        // Calculate Total
        double total = expenses.stream().mapToDouble(ExpenseState::getAmount).sum();
        
        System.out.println("\n--- Total Spent: $" + String.format("%.2f", total) + " ---");
        
        if (expenses.isEmpty()) {
            System.out.println("(No expenses recorded)");
        } else {
            // Show last 5 expenses only to keep screen clean, or all if you prefer
            expenses.forEach(System.out::println);
        }

        System.out.println("\nOptions:");
        System.out.println("1. Add Expense");
        System.out.println("2. Remove Expense");
        System.out.println("3. Save to JSON");
        System.out.println("4. Load from JSON");
        System.out.println("5. Exit");
        System.out.print("Select: ");
    }

    private void handleInput() {
        try {
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": addExpense(); break;
                case "2": removeExpense(); break;
                case "3": exportData(); break;
                case "4": importData(); break;
                case "5": running = false; break;
                default: System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addExpense() {
        try {
            System.out.print("Description: ");
            String desc = scanner.nextLine();
            
            System.out.print("Category (e.g., Food, Transport): ");
            String cat = scanner.nextLine();

            System.out.print("Amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            int nextId = expenses.size() + 1; // Simple ID generation
            
            ExpenseState newExpense = new ExpenseState(nextId, desc, amount, cat);
            expenses.add(newExpense);
            System.out.println("Expense added!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
        }
    }

    private void removeExpense() {
        System.out.print("Enter ID to remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean removed = expenses.removeIf(e -> e.getId() == id);
            if (removed) System.out.println("Expense removed.");
            else System.out.println("ID not found.");
        } catch (Exception e) {
            System.out.println("Invalid ID.");
        }
    }

    private void exportData() {
        try {
            context.setData(expenses);
            context.exportData("json");
            System.out.println("Data saved to Expense.json");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private void importData() {
        try {
            context.importData("json");
            List<ExpenseState> data = context.getData();
            if (data != null) {
                expenses = new ArrayList<>(data);
                System.out.println("Data loaded successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}