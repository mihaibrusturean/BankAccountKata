package com.exaltit.interview;

import com.exaltit.interview.model.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BankTest {

    @Test
    public void testMakeDepositPositive() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        bank.makeDeposit(bankAccount, 100.0);
        assertEquals(bankAccount.getBalance(), 100.0);
        assertEquals(bankAccount.getOperations().size(), 1);
        Operation operation = bankAccount.getOperations().get(0);
        assertEquals(operation.getOperationType(), OperationType.DEPOSIT);
        assertEquals(operation.getOperationState(), OperationState.APPROVED);
    }

    @Test
    public void testMakeDepositNegative() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        bank.makeDeposit(bankAccount, -100.0);
        assertEquals(bankAccount.getBalance(), 0.0);
        assertEquals(bankAccount.getOperations().size(), 1);
        Operation operation = bankAccount.getOperations().get(0);
        assertEquals(operation.getOperationType(), OperationType.DEPOSIT);
        assertEquals(operation.getOperationState(), OperationState.DENIED);
    }

    @Test
    public void testMakeWithdrawalFromEmpty() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        bank.makeWithdrawal(bankAccount, 100.0);
        assertEquals(bankAccount.getBalance(), 0.0);
        assertEquals(bankAccount.getOperations().size(), 1);
        Operation operation = bankAccount.getOperations().get(0);
        assertEquals(operation.getOperationType(), OperationType.WITHDRAWAL);
        assertEquals(operation.getOperationState(), OperationState.DENIED);
    }

    @Test
    public void testMakeWithdrawalAfterDeposit() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        bank.makeDeposit(bankAccount, 100.0);
        assertEquals(bankAccount.getBalance(), 100.0);
        bank.makeWithdrawal(bankAccount, 100.0);
        assertEquals(bankAccount.getBalance(), 0.0);
        assertEquals(bankAccount.getOperations().size(), 2);
        Operation operation = bankAccount.getOperations().get(1);
        assertEquals(operation.getOperationType(), OperationType.WITHDRAWAL);
        assertEquals(operation.getOperationState(), OperationState.APPROVED);
    }

    @Test
    public void testMakeWithdrawalNegative() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        bank.makeWithdrawal(bankAccount, -100.0);
        assertEquals(bankAccount.getBalance(), 0.0);
        assertEquals(bankAccount.getOperations().size(), 1);
        Operation operation = bankAccount.getOperations().get(0);
        assertEquals(operation.getOperationType(), OperationType.WITHDRAWAL);
        assertEquals(operation.getOperationState(), OperationState.DENIED);
    }
}
