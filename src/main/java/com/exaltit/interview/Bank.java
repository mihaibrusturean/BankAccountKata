package com.exaltit.interview;

import com.exaltit.interview.model.BankAccount;
import com.exaltit.interview.model.Operation;
import com.exaltit.interview.model.OperationType;

public class Bank {

    // operations could be made static, but I am working with the assumption that we will add a list of clients and
    // bank accounts in this class at some point

    private void makeOperation(OperationType operationType, BankAccount bankAccount, double amount) {
        Operation operation = new Operation(bankAccount.getClient(), operationType, bankAccount.getBalance(), amount);
        if (operation.isOperationValid()) {
            operation.markAsApproved();
            bankAccount.addOperation(operation);
            bankAccount.setNewBalance(operation.getBalanceAfterOperation());
        } else {
            operation.markAsDenied();
            bankAccount.addOperation(operation);
        }
    }

    public void makeDeposit(BankAccount bankAccount, double amount) {
        makeOperation(OperationType.DEPOSIT, bankAccount, amount);
    }

    public void makeWithdrawal(BankAccount bankAccount, double amount) {
        makeOperation(OperationType.WITHDRAWAL, bankAccount, amount);
    }

    public String formatAccountOperations(BankAccount bankAccount) {
        return "";
    }
}
