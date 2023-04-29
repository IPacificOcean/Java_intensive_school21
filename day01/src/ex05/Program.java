package ex05;


import java.util.Scanner;
import java.util.UUID;

import static ex05.Transaction.transferCategory.credits;

public class Program {
    public static void main(String[] args) {

        TransactionsService tService = new TransactionsService();
        Scanner scanner = new Scanner(System.in);

        int index = 0;
        while (true) {
            System.out.println("1.Add a user");
            System.out.println("2.View user balances");
            System.out.println("3.Perform a transfer");
            System.out.println("4.View all transactions for a specific user");
            System.out.println("5.DEV - remove a transfer by ID");
            System.out.println("6.DEV - check transfer validity");
            System.out.println("7.Finish execution");

            String  itemMenu = scanner.next();

            int uid;
            String name;
            long balance;
            try {
                switch (itemMenu) {
                    case "1":
                        System.out.println("Enter a user name and a balance");
                        name = scanner.next();
                        balance = scanner.nextLong();
                        tService.addUser(name, balance);
                        uid = tService.getUserList_().retrieveUserByIndex(index).getId();
                        System.out.println("User whiths id = " + uid + " is added");
                        System.out.println("-----------------------------------");
                        break;
                    case "2":
                        System.out.println("Enter a user ID");
                        uid = scanner.nextInt();
                        name = tService.getUserList_().retrieveUserById(uid).getName();
                        balance = tService.getUserList_().retrieveUserById(uid).getBalance();
                        System.out.println(name + " - " + balance);
                        System.out.println("-----------------------------------");
                        break;
                    case "3":
                        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                        int senderId = scanner.nextInt();
                        int recipientId = scanner.nextInt();
                        long transferAmount = scanner.nextLong();
                        tService.transfer(senderId, recipientId, transferAmount);
                        System.out.println("The transfer is completed");
                        System.out.println("-----------------------------------");
                        break;
                    case "4":
                        System.out.println("Enter a user ID");
                        uid = scanner.nextInt();
                        Transaction[] arr = tService.getTransactions(uid);
                        for (Transaction a : arr) {
                            if (a.getCategory() == credits) {
                            System.out.println("To " + a.getRecipient().getName() + "(id = " + a.getRecipient().getId() + ") " + a.getTransfer_amount() + " with id = " + a.getTid());
                            } else {
                                System.out.println("From " + a.getSender().getName() + "(id = " + a.getSender().getId() + ") " + a.getTransfer_amount() + " with id = " + a.getTid());
                            }
                        }
                        System.out.println("-----------------------------------");
                        break;
                    case "5":
                        System.out.println("Enter a user ID and a transfer ID");
                        uid = scanner.nextInt();
                        String tid = scanner.next();
                        tService.removeTransaction(uid, UUID.fromString(tid));
//                        if (tService.getUserList_().retrieveUserById(uid).getMyTransactions(). == credits) {
//                            System.out.println("To " + a.getRecipient().getName() + "(id = " + a.getRecipient().getId() + ") " + a.getTransfer_amount() + " with id = " + a.getTid());
//                        } else {
//                            System.out.println("From " + a.getSender().getName() + "(id = " + a.getSender().getId() + ") " + a.getTransfer_amount() + " with id = " + a.getTid());
//                        }
                        System.out.println("-----------------------------------");
                        break;
                    case "6":
                        System.out.println("-----------------------------------");
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



//        System.out.println();
//        System.out.println("Исключения:");
//        try {
//            tService.transfer(tService.getUserList_().retrieveUserById(2), tService.getUserList_().retrieveUserById(1), 5000);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            tService.addUser("Den", -3000);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
                                                                                                                                                                                                                                                                                                                                  