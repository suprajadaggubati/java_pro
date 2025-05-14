import java.util.*;

public class ATMService {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, User> userDB = new HashMap<>();
    private User currentUser;

    public ATMService() {
        // Simulate a few users
        userDB.put(952141, new User(952141, 191904));
        userDB.put(989947, new User(989947, 717976));
    }

    public void start() {
        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.print("Enter your Customer Number: ");
            int customerNumber = safeReadInt();

            System.out.print("Enter your PIN: ");
            int pin = safeReadInt();

            if (authenticate(customerNumber, pin)) {
                System.out.println("\nLogin successful!\n");
                showMainMenu();
                break;
            } else {
                System.out.println("Invalid login. Please try again.\n");
            }
        }
    }

    private boolean authenticate(int customerNumber, int pin) {
        if (userDB.containsKey(customerNumber)) {
            User user = userDB.get(customerNumber);
            if (user.getPin() == pin) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    private void showMainMenu() {
        int choice;
        do {
            System.out.println("Select Account Type:");
            System.out.println("1 - Checking Account");
            System.out.println("2 - Saving Account");
            System.out.println("3 - Exit");
            System.out.print("Choice: ");
            choice = safeReadInt();

            switch (choice) {
                case 1 -> handleChecking();
                case 2 -> handleSaving();
                case 3 -> System.out.println("Thank you for using the ATM. Goodbye!");
                default -> System.out.println("Invalid choice. Try again.\n");
            }
        } while (choice != 3);
    }

    private void handleChecking() {
        int choice;
        do {
            System.out.println("\nChecking Account:");
            System.out.println("1 - View Balance");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Deposit");
            System.out.println("4 - Back to Main Menu");
            System.out.print("Choice: ");
            choice = safeReadInt();

            switch (choice) {
                case 1 -> System.out.println("Balance: " + currentUser.getAccount().getFormattedCheckingBalance());
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amt = safeReadDouble();
                    if (currentUser.getAccount().withdrawFromChecking(amt)) {
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amt = safeReadDouble();
                    currentUser.getAccount().depositToChecking(amt);
                    System.out.println("Deposit successful.");
                }
                case 4 -> {}
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private void handleSaving() {
        int choice;
        do {
            System.out.println("\nSaving Account:");
            System.out.println("1 - View Balance");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Deposit");
            System.out.println("4 - Back to Main Menu");
            System.out.print("Choice: ");
            choice = safeReadInt();

            switch (choice) {
                case 1 -> System.out.println("Balance: " + currentUser.getAccount().getFormattedSavingBalance());
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amt = safeReadDouble();
                    if (currentUser.getAccount().withdrawFromSaving(amt)) {
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amt = safeReadDouble();
                    currentUser.getAccount().depositToSaving(amt);
                    System.out.println("Deposit successful.");
                }
                case 4 -> {}
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private int safeReadInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Discard invalid
        }
        return scanner.nextInt();
    }

    private double safeReadDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next(); // Discard invalid
        }
        return scanner.nextDouble();
    }
}
