package ex04;


import static ex04.Transaction.transferCategory.credits;
import static ex04.Transaction.transferCategory.debits;

public class Program {
    public static void main(String[] args) {
//        int transfer_amount = -500;
//        Transaction transaction1 = new Transaction(user, user2, transfer_amount, credits);
//        transfer_amount = 1000;
//        Transaction transaction2 = new Transaction(user2, user, transfer_amount, debits);
//        transfer_amount = 1500;
//        Transaction transaction3 = new Transaction(user3, user, transfer_amount, debits);
//        Transaction transaction4 = new Transaction(user, user3, transfer_amount, debits);
//
//        TransactionsLinkedList tlist = new TransactionsLinkedList();
//        tlist.addTransaction(transaction1);
//        tlist.addTransaction(transaction2);
//        tlist.addTransaction(transaction3);
//
//        System.out.println("Добавление:");
//        print(tlist);
//
//        tlist.removeTransaction(transaction3.getTid());
//        System.out.println();
//        System.out.println("Удаление:");
//        print(tlist);
//        System.out.println();
//
//        Transaction[] arr = tlist.toArray();
//        System.out.println("Массив:");
//        for (Transaction transaction : arr) {
//            System.out.println(transaction);
//        }
//        System.out.println();
//
//        System.out.println("Исключение:");
//        try {
//            tlist.removeTransaction(transaction4.getTid());
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    static void print(TransactionsLinkedList list) {
//        Transaction l = list.getHead_();
//        for (int i = 0; i < list.getSize_(); ++i) {
//            System.out.println(l);
//            l = l.next;
//        }
        System.out.println("Добавление:");
        TransactionsService tService = new TransactionsService();
        tService.addUser("Mike", 1000);
        tService.addUser("Robert", 2000);
        tService.addUser("Ivan", 3000);
        System.out.println("Кол-во пользователей: " + tService.getUserList_().retrieveNumberOfUsers());
        for (int i = 1; i <= tService.getUserList_().retrieveNumberOfUsers(); ++i) {
            System.out.println("Баланс пользователя id " + i + ": " + tService.retrieveUserBalans(i));
        }
        System.out.println();
        System.out.println("Трансфер:");
        tService.transfer(tService.getUserList_().retrieveUserById(1), tService.getUserList_().retrieveUserById(2), 200);
        for (int i = 1; i <= tService.getUserList_().retrieveNumberOfUsers(); ++i) {
            System.out.println("Баланс пользователя id " + i + ": " + tService.retrieveUserBalans(i));
        }
        System.out.println();
        System.out.println("Массив трансферов:");
        Transaction[] arr = tService.getTransactions(1);
        for (Transaction a : arr) {
            System.out.println(a);
            System.out.println();
        }
    }
}
                                                                                                                                                                                                                                                                                                                                  