package com.jminiapp.examples.expense;

import com.jminiapp.core.adapters.JSONAdapter;

public class ExpenseJSONAdapter implements JSONAdapter<ExpenseState> {
    @Override
    public Class<ExpenseState> getstateClass() {
        return ExpenseState.class;
    }
}