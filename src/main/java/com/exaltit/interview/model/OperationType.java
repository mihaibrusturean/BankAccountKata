package com.exaltit.interview.model;

public enum OperationType {
    DEPOSIT {
        public double apply(double balance, double amount) {
            return balance + amount;
        }

        public boolean isValidInput(double balance, double amount) {
            return amount > 0;
        }
    }, 
    WITHDRAWAL {
        public double apply(double balance, double amount) {
            return balance - amount;
        }

        public boolean isValidInput(double balance, double amount) {
            return amount > 0  && balance - amount >= 0;
        }
    };
    
    public abstract double apply(double balance, double amount);

    public abstract boolean isValidInput(double balance, double amount);
}
