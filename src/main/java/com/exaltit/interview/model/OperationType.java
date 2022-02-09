package com.exaltit.interview.model;

public enum OperationType {
    DEPOSIT {
        public double apply(double balance, double amount) {
            return balance + amount;
        }
    }, 
    WITHDRAWAL {
        public double apply(double balance, double amount) {
            return balance - amount;
        }
    };
    
    public abstract double apply(double balance, double amount);
}
