package com.exaltit.interview.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount {
    private Client client;
    private String id;
    private double balance;
    private List<Operation> operations;

    public BankAccount(Client client) {
        this.client = client;
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.balance = 0;
        this.operations = new ArrayList<>();
    }

    public Client getClient() {
        return client;
    }

    public double getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    public void setNewBalance(double newBalance) {
        this.balance = newBalance;
    }
}
