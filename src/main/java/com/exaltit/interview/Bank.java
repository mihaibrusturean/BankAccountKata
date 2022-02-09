package com.exaltit.interview;

import com.exaltit.interview.model.BankAccount;
import com.exaltit.interview.model.Operation;
import com.exaltit.interview.model.OperationType;

public class Bank {
    public void makeDeposit(BankAccount bankAccount, double amount) {
        Operation operation = new Operation(bankAccount.getClient(), OperationType.DEPOSIT, bankAccount.getBalance(), amount);
        if (operation.isOperationValid()) {
            operation.markAsApproved();
            bankAccount.addOperation(operation);
            bankAccount.setNewBalance(operation.getBalanceAfterOperation());
        } else {
            operation.markAsDenied();
            bankAccount.addOperation(operation);
        }
    }
}
