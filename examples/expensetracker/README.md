# 💸 Expense Tracker Example

A financial tracking application built with the **JMiniApp framework**.

## Overview

This example demonstrates how to create a management application using JMiniApp Core. Unlike the simple Counter example, this project manages a **list of objects** (expenses), calculates totals dynamically, and handles structured data persistence.

## 🚀 Features

* **➕ Add Expense**: Record new expenses with description, category, and amount
* **🗑️ Remove Expense**: Delete specific entries by their ID
* **💰 Track Total**: Automatically calculates and displays the sum of all expenses
* **💾 Data Persistence**: Automatically loads data on startup and saves on shutdown
* **📤 Export / 📥 Import**: Manual options to save or reload data from `Expense.json`
* **📋 List Management**: Displays categorized records in the console

## 🗂️ Project Structure

```text
expensetracker/
├── pom.xml
├── README.md
└── src/main/java/com/jminiapp/examples/expense/
    ├── ExpenseApp.java          # Main application logic & UI
    ├── ExpenseAppRunner.java    # Bootstrap configuration
    ├── ExpenseState.java        # Data model (POJO)
    ├── ExpenseJSONAdapter.java  # JSON serialization adapter
```

##  Key Components

### 🧾 ExpenseState

A model class representing a single financial record containing:

* `id`: Unique identifier for the expense
* `description`: What the money was spent on
* `category`: Grouping (e.g., Food, Transport)
* `amount`: The cost value

### 🔄 ExpenseJSONAdapter

A format adapter enabling JSON import/export:

* Implements `JSONAdapter<ExpenseState>`
* Maps the data model to the framework serializer
* Ensures the list of expenses is correctly converted to/from JSON

### 🖥️ ExpenseApp

The core application class that extends `JMiniApp`:

* `initialize()`: Sets up input and loads existing data
* `run()`: Handles the main loop and user input
* `displayMenu()`: Shows expenses and calculates totals using streams
* `shutdown()`: Saves the latest data before exit

### 🚀 ExpenseAppRunner

Bootstrap class that:

* Registers the adapter with `.withAdapters()`
* Sets the application name to **"Expense"** → output file is `Expense.json`
* Launches the application lifecycle

## 🛠️ Building and Running

### 📋 Prerequisites

* Java 17 or higher
* Maven 3.6 or higher

### 🏗️ Build the project

```bash
mvn clean package
```

### ▶️ Run the application

**Option 1: Packaged JAR**

```bash
java -jar target/expense-tracker.jar
```

**Option 2: Maven exec plugin**

```bash
mvn exec:java -Dexec.mainClass="com.jminiapp.examples.expense.ExpenseAppRunner"
```

## 💡 Usage Example

### ✏️ Basic Operations

```text
=== Personal Expense Tracker ===
Loaded 0 records.

--- Total Spent: $0.00 ---
(No expenses recorded)

Options:
1. Add Expense
2. Remove Expense
3. Save to JSON
4. Load from JSON
5. Exit
Select: 1

Description: Lunch
Category (e.g., Food, Transport): Food
Amount: 15.50
Expense added!
```

### 📊 Viewing and Tracking Totals

```text
--- Total Spent: $15.50 ---
[1] Lunch (Food): $15.50

Options:
1. Add Expense
2. Remove Expense
3. Save to JSON
4. Load from JSON
5. Exit
Select: 1

Description: Netflix
Category (e.g., Food, Transport): Entertainment
Amount: 12.99
Expense added!

--- Total Spent: $28.49 ---
[1] Lunch (Food): $15.50
[2] Netflix (Entertainment): $12.99
```

### 📤 Exporting Data

When saving or exiting, data is stored in `Expense.json`.

```json
[
  {
    "id": 1,
    "description": "Lunch",
    "amount": 15.5,
    "category": "Food"
  },
  {
    "id": 2,
    "description": "Netflix",
    "amount": 12.99,
    "category": "Entertainment"
  }
]
```


## 👤 Author 
Developed by Angel Leandro Puch Uribe as part of the JMiniApp examples suite
