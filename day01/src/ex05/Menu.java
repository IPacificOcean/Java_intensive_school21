package ex05;

import java.util.Scanner;
import java.util.UUID;

import static ex05.Transaction.transferCategory.credits;

public class Menu {

    private final TransactionsService tService = new TransactionsService();
    private final Scanner scanner = new Scanner(System.in);
    private String mode_ = "";

    void startProgram() {
        int index = 0;
        while (true) {
            printMenu();
            String itemMenu = scanner.next();
            try {
                switch (itemMenu) {
                    case "1":
                        addUser(index);
                        break;
                    case "2":
                        userBalans();
                        break;
                    case "3":
                        transact();
                        break;
                    case "4":
                        userTransactions();
                        break;
                    case "5":
                        scanner.close();
                        System.exit(0);
                    default:
                        System.err.println("Illegal argumentt");
                        System.out.println("Try again");
                        System.out.println("-----------------------------------");
                }
            } catch (RuntimeException e) {
                scanner.tokens();
                System.err.println("Illegal argumenttt");
                System.out.println("Try again");
                System.out.println("-----------------------------------");
            }
            ++index;
        }
    }

    void startDevMode() {
        int index = 0;
        while (true) {
            printMenu();
            String itemMenu = scanner.next();
            try {
                switch (itemMenu) {
                    case "1":
                        addUser(index);
                        break;
                    case "2":
                        userBalans();
                        break;
                    case "3":
                        transact();
                        break;
                    case "4":
                        userTransactions();
                        break;
                    case "5":
                        removeTransfer();
                        break;
                    case "6":
                        checkTransfer();
                        break;

                    case "7":
                        scanner.close();
                        System.exit(0);
                    default:
                        System.err.println("Illegal argumentt");
                        System.out.println("Try again");
                        System.out.println("-----------------------------------");
                }
            } catch (RuntimeException e) {
                scanner.tokens();
                System.err.println("Illegal argumenttt");
                System.out.println("Try again");
                System.out.println("-----------------------------------");
            }

            ++index;
        }
    }


    void printMenu() {
        String item1 = "1.Add a user";
        String item2 = "2.View user balances";
        String item3 = "3.Perform a transfer";
        String item4 = "4.View all transactions for a specific user";
        String item5 = "5.DEV - remove a transfer by ID";
        String item6 = "6.DEV - check transfer validity";
        String item7dev = "7.Finish execution";
        String item7 = "5.Finish execution";
        if (mode_.equals("--profile=dev")) {
            System.out.println(item1 + "\n" + item2 + "\n" + item3 + "\n" + item4 + "\n" + item5 + "\n" + item6 + "\n" + item7dev);
        } else {
            System.out.println(item1 + "\n" + item2 + "\n" + item3 + "\n" + item4 + "\n" + item7);
        }
    }

    void addUser(int index) {
        System.out.println("Enter a user name and a balance");
        String name = scanner.next();
        long balance = scanner.nextLong();
        tService.addUser(name, balance);
        int uid = tService.getUserList_().retrieveUserByIndex(index).getId();
        System.out.println("User whiths id = " + uid + " is added");
        System.out.println("-----------------------------------");
    }

    void userBalans() {
        System.out.println("Enter a user ID");
        int uid = scanner.nextInt();
        String name = tService.getUserList_().retrieveUserById(uid).getName();
        long balance = tService.getUserList_().retrieveUserById(uid).getBalance();
        System.out.println(name + " - " + balance);
        System.out.println("-----------------------------------");

    }

    void transact() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        int senderId = scanner.nextInt();
        int recipientId = scanner.nextInt();
        long transferAmount = scanner.nextLong();
        tService.transfer(senderId, recipientId, transferAmount);
        System.out.println("The transfer is completed");
        System.out.println("-----------------------------------");
    }

    void userTransactions() {
        System.out.println("Enter a user ID");
        int uid = scanner.nextInt();
        Transaction[] arr = tService.getTransactions(uid);
        for (Transaction a : arr) {
            if (a.getCategory() == credits) {
                System.out.println("To " + a.getRecipient().getName() + "(id = " + a.getRecipient().getId() + ") " + a.getTransfer_amount() + " with id = " + a.getTid());
            } else {
                System.out.println("From " + a.getSender().getName() + "(id = " + a.getSender().getId() + ") " + a.getTransfer_amount() + " with id = " + a.getTid());
            }
        }
        System.out.println("-----------------------------------");
    }

    void removeTransfer() {
        System.out.println("Enter a user ID and a transfer ID");
        int uid = scanner.nextInt();
        String tid = scanner.next();
        Transaction[] arr1 = tService.getTransactions(uid);
        for (Transaction a : arr1) {
            if (a.getTid().equals(UUID.fromString(tid))) {
                if (a.getCategory() == credits) {
                    System.out.println("To " + a.getRecipient().getName() + "(id = " + a.getRecipient().getId() + ") " + a.getTransfer_amount() + " removed");
                } else {
                    System.out.println("From " + a.getSender().getName() + "(id = " + a.getSender().getId() + ") " + a.getTransfer_amount() + " removed");
                }
            }
        }
        tService.removeTransaction(uid, UUID.fromString(tid));
        System.out.println("-----------------------------------");
    }

    void checkTransfer() {
        Transaction[] arr2 = tService.getNotValidTransaction();
        for (Transaction a : arr2) {
            if (a.getCategory() == credits) {
                System.out.println("Check results: \n" + a.getSender().getName() + "(id = " + a.getSender().getId() + ") " + "has unacknowledged transfer id = " + a.getTid());
            } else {
                System.out.println("Check results: \n" + a.getRecipient().getName() + "(id = " + a.getRecipient().getId() + ") " + "has unacknowledged transfer id = " + a.getTid());
            }
        }
        System.out.println("-----------------------------------");
    }

    void startApp(String[] args) {
        if (args.length > 0) {
            mode_ = args[0];
        }
        if (mode_.equals("--profile=dev")) {
            startDevMode();
        } else {
            startProgram();
        }
    }
}
