package com.jminiapp.examples.expense;

import com.jminiapp.core.engine.JMiniAppRunner;

public class ExpenseAppRunner {
    public static void main(String[] args) {
        JMiniAppRunner
            .forApp(ExpenseApp.class)
            .withState(ExpenseState.class)
            .withAdapters(new ExpenseJSONAdapter())
            .named("Expense") // Output file: Expense.json
            .run(args);
    }
}