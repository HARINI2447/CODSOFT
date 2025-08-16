package game;
import java.util.Scanner;

//Class representing a Bank Account
class BankAccount{
 private double balance;

 // Constructor
 public BankAccount(double initialBalance) {
     this.balance = initialBalance;
 }

 // Deposit money
 public void deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         System.out.println("Successfully deposited: $" + amount);
     } else {
         System.out.println("Deposit amount must be greater than 0.");
     }
 }

 // Withdraw money
 public void withdraw(double amount) {
     if (amount <= 0) {
         System.out.println("Withdrawal amount must be greater than 0.");
     } else if (amount > balance) {
         System.out.println("Insufficient balance!");
     } else {
         balance -= amount;
         System.out.println("Successfully withdrew: $" + amount);
     }
 }
//Check balance
 public double getBalance() {
     return balance;
 }
}
class ATM {
 private BankAccount account;

 public ATM(BankAccount account) {
     this.account = account;
 }

 public void showMenu() {
     Scanner scanner = new Scanner(System.in);
     int choice;

     do {
         System.out.println("\n=== ATM Menu ===");
         System.out.println("1. Check Balance");
         System.out.println("2. Deposit Money");
         System.out.println("3. Withdraw Money");
         System.out.println("4. Exit");
         System.out.print("Enter your choice: ");
         choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 System.out.println("Your balance is: $" + account.getBalance());
                 break;
             case 2:
                 System.out.print("Enter deposit amount: ");
                 double depositAmount = scanner.nextDouble();
                 account.deposit(depositAmount);
                 break;
             case 3:
                 System.out.print("Enter withdrawal amount: ");
                 double withdrawAmount = scanner.nextDouble();
                 account.withdraw(withdrawAmount);
                 break;
             case 4:
                 System.out.println("Thank you for using the ATM. Goodbye!");
                 break;
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     } while (choice != 4);

     scanner.close();
 }
}
public class ATMInterface {
 public static void main(String[] args) {
	 BankAccount userAccount = new BankAccount(500.0); // Initial balance
     ATM atm = new ATM(userAccount);
     atm.showMenu();
 }
}
