#  Expense Tracker Example Application

A financial tracking application demonstrating **list management**, **data aggregation**, and **JSON persistence** using **JMiniApp**.

##  Features

*  Record expenses with categories
*  Calculate total spending
*  Remove entries by ID
*  JSON import/export
*  Automatic list persistence

 **Source Code:** [examples/expensetracker](https://github.com/Lea2010-X/jminiapp/tree/main/examples/expensetracker)

---

##  Key Concepts Demonstrated

*  Managing collections/lists in state
*  Data aggregation (calculating sums)
*  Complex object modeling (POJOs)
*  Interactive console UI
*  Framework-based list persistence

---

##  Quick Start

```bash
cd examples/expensetracker
mvn clean package
java -jar target/expense-tracker.jar
```

---

##  Code Highlights

###  State Model

```java
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

    public double getAmount() { return amount; }
    // ... getters and toString()
}
```

###  Application Logic

```java
public class ExpenseApp extends JMiniApp {
    private List<ExpenseState> expenses;
    private boolean running;
    private Scanner scanner;

    public ExpenseApp(JMiniAppConfig config) {
        super(config);
    }

    @Override
    protected void initialize() {
        expenses = new ArrayList<>();
        scanner = new Scanner(System.in);
        running = true;

        // Load previous data automatically
        List<ExpenseState> data = context.getData();
        if (data != null && !data.isEmpty()) {
            expenses.addAll(data);
            System.out.println("Loaded " + expenses.size() + " records.");
        }
    }

    @Override
    protected void run() {
        while (running) {
            // Calculate Total dynamically
            double total = expenses.stream()
                .mapToDouble(ExpenseState::getAmount)
                .sum();

            System.out.println("
--- Total Spent: $" + String.format("%.2f", total) + " ---");
            // ... Display menu and handle input
        }
    }

    @Override
    protected void shutdown() {
        context.setData(expenses); // Save list state
        scanner.close();
        System.out.println("Saving data... Goodbye!");
    }
}
```

###  Bootstrap

```java
public static void main(String[] args) {
    JMiniAppRunner
        .forApp(ExpenseApp.class)
        .withState(ExpenseState.class)
        .withAdapters(new ExpenseJSONAdapter())
        .named("Expense") // Defines output as Expense.json
        .run(args);
}
```
