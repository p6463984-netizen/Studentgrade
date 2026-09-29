import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task 3: Bank Account Simulation
 * ---------------------------------
 * This program simulates a simple bank account using a class (BankAccount)
 * that supports deposit, withdrawal, and balance-check operations.
 * It validates that withdrawals never exceed the available balance,
 * and prints a clear transaction history at the end.
 */
public class BankAccountSimulation {

    // ---------- BankAccount class ----------
    // Represents a bank account with a balance and a transaction history
    static class BankAccount {
        private String accountHolder;
        private double balance;
        private ArrayList<String> transactionHistory;

        // Constructor: sets up a new account with an initial balance
        public BankAccount(String accountHolder, double initialBalance) {
            this.accountHolder = accountHolder;
            this.balance = initialBalance;
            this.transactionHistory = new ArrayList<>();
            transactionHistory.add(String.format("Account opened with balance: %.2f", initialBalance));
        }

        // Deposits money into the account
        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Deposit amount must be greater than zero.");
                return;
            }
            balance += amount;
            transactionHistory.add(String.format("Deposited: %.2f | New Balance: %.2f", amount, balance));
            System.out.printf("Deposit successful. New balance: %.2f%n", balance);
        }

        // Withdraws money from the account, with validation against overdrawing
        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be greater than zero.");
                return;
            }
            // Validation: prevent withdrawals exceeding the available balance
            if (amount > balance) {
                System.out.println("Withdrawal denied: insufficient funds. " +
                        "Available balance: " + String.format("%.2f", balance));
                transactionHistory.add(String.format("Failed withdrawal attempt: %.2f (insufficient funds)", amount));
                return;
            }
            balance -= amount;
            transactionHistory.add(String.format("Withdrew: %.2f | New Balance: %.2f", amount, balance));
            System.out.printf("Withdrawal successful. New balance: %.2f%n", balance);
        }

        // Returns the current balance
        public double checkBalance() {
            return balance;
        }

        // Prints the full transaction history
        public void printTransactionHistory() {
            System.out.println("\n===== Transaction History for " + accountHolder + " =====");
            for (String entry : transactionHistory) {
                System.out.println("- " + entry);
            }
            System.out.printf("Final Balance: %.2f%n", balance);
        }
    }

    // ---------- Main program ----------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Bank Account Simulation =====");

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine().trim();

        double initialBalance = readNonNegativeDouble(scanner, "Enter initial deposit amount: ");
        BankAccount account = new BankAccount(name, initialBalance);

        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    double depositAmount = readNonNegativeDouble(scanner, "Enter amount to deposit: ");
                    account.deposit(depositAmount);
                    break;
                case "2":
                    double withdrawAmount = readNonNegativeDouble(scanner, "Enter amount to withdraw: ");
                    account.withdraw(withdrawAmount);
                    break;
                case "3":
                    System.out.printf("Current Balance: %.2f%n", account.checkBalance());
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }

        // Print the full transaction history before closing
        account.printTransactionHistory();

        System.out.println("\nThank you for using the Bank Account Simulation!");
        scanner.close();
    }

    /**
     * Keeps prompting until the user enters a valid non-negative number.
     */
    private static double readNonNegativeDouble(Scanner scanner, String prompt) {
        double value = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Amount cannot be negative.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        return value;
    }
}
