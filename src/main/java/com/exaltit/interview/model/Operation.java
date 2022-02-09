package com.exaltit.interview.model;

import java.time.ZonedDateTime;

public class Operation {
    private Client client;
    private OperationType operationType;
    private double balance;
    private double amount;
    private ZonedDateTime timestamp;
    private OperationState operationState;

    public Operation(Client client, OperationType operationType, double balance, double amount) {
        this.client = client;
        this.operationType = operationType;
        this.balance = balance;
        this.amount = amount;
        this.timestamp = ZonedDateTime.now();
        this.operationState = OperationState.PENDING;
    }

    public Client getClient() {
        return client;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public OperationState getOperationState() {
        return operationState;
    }

    //we don't want any setters on this class because we don't want to make any changes to an Operation after it was created

    public boolean isOperationValid() {
        return this.operationType.isValidInput(balance, amount);
    }

    public double getBalanceAfterOperation() {
        return this.operationType.apply(balance, amount);
    }

    public void markAsApproved() {
        this.operationState = OperationState.APPROVED;
    }

    public void markAsDenied() {
        this.operationState = OperationState.DENIED;
    }
}
