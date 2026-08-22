class BankAccount {
    private int accountNumber;
    private String holderName;
    protected double balance;

    // Constructor
    BankAccount(int accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("$" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Display account details
    public void displayAccount() {
        System.out.println("----------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Holder Name    : " + holderName);
        System.out.println("Balance        : $" + balance);
        System.out.println("----------------------------");
    }
}

// Savings Account
class SavingsAccount extends BankAccount {

    SavingsAccount(int accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= 1000) {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn from Savings Account.");
        } else {
            System.out.println("Withdrawal denied! Minimum balance of $1000 must be maintained.");
        }
    }
}

// Current Account
class CurrentAccount extends BankAccount {

    CurrentAccount(int accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn from Current Account.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        // Runtime Polymorphism
        BankAccount account1 = new SavingsAccount(101, "Ahad", 5000);
        BankAccount account2 = new CurrentAccount(102, "Amit", 3000);

        System.out.println("Before Transactions");
        account1.displayAccount();
        account2.displayAccount();

        System.out.println("\nTransactions");

        account1.deposit(1000);
        account1.withdraw(4500);

        account2.deposit(500);
        account2.withdraw(2500);

        System.out.println("\nAfter Transactions");
        account1.displayAccount();
        account2.displayAccount();
    }
}