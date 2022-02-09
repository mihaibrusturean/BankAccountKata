package com.exaltit.interview;

import com.exaltit.interview.model.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

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

    @Test
    public void testFormatEmptyOperations() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        String formattedOperations = bank.formatAccountOperations(bankAccount);
        assertEquals(formattedOperations, "Operations:[]");
    }

    @Test
    public void testFormatSingleOperationApproved() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        bank.makeDeposit(bankAccount, 100.0);
        String formattedOperations = bank.formatAccountOperations(bankAccount);
        assertTrue(formattedOperations.startsWith("Operations"));
        assertTrue(formattedOperations.contains("TYPE:DEPOSIT,BALANCE:0.0,AMOUNT:100.0,STATUS:APPROVED"));
    }

    @Test
    public void testFormatSingleOperationDenied() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        bank.makeDeposit(bankAccount, -100.0);
        String formattedOperations = bank.formatAccountOperations(bankAccount);
        assertTrue(formattedOperations.startsWith("Operations"));
        assertTrue(formattedOperations.contains("TYPE:DEPOSIT,BALANCE:0.0,AMOUNT:100.0,STATUS:DENIED"));
    }

    @Test
    public void testFormatMultipleOperations() {
        Bank bank = new Bank();
        Client client = new Client("Client1");
        BankAccount bankAccount = new BankAccount(client);
        bank.makeDeposit(bankAccount, 100.0);
        bank.makeWithdrawal(bankAccount, 100.0);
        String formattedOperations = bank.formatAccountOperations(bankAccount);
        assertTrue(formattedOperations.startsWith("Operations"));
        assertTrue(formattedOperations.contains("TYPE:DEPOSIT,BALANCE:0.0,AMOUNT:100.0,STATUS:APPROVED"));
        assertTrue(formattedOperations.contains("TYPE:WITHDRAWAL,BALANCE:100.0,AMOUNT:100.0,STATUS:APPROVED"));
    }
}
