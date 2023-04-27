package ex03;


import static ex03.Transaction.transferCategory.debits;
import static ex03.Transaction.transferCategory.credits;

public class Program {
    public static void main(String[] args) {
        User user = new User("Mike", 1000);
        User user2 = new User("Robert", 2000);
        User user3 = new User("Ivan", 3000);

        int transfer_amount = -500;
        Transaction transaction1 = new Transaction(user, user2, transfer_amount, credits);
        transfer_amount = 1000;
        Transaction transaction2 = new Transaction(user2, user, transfer_amount, debits);
        transfer_amount = 1500;
        Transaction transaction3 = new Transaction(user3, user, transfer_amount, debits);
        Transaction transaction4 = new Transaction(user, user3, transfer_amount, debits);

        TransactionsLinkedList tlist = new TransactionsLinkedList();
        tlist.addTransaction(transaction1);
        tlist.addTransaction(transaction2);
        tlist.addTransaction(transaction3);

        System.out.println("Добавление:");
        print(tlist);

        tlist.removeTransaction(transaction3.getTid());
        System.out.println();
        System.out.println("Удаление:");
        print(tlist);
        System.out.println();

        Transaction[] arr = tlist.toArray();
        System.out.println("Массив:");
        for (Transaction transaction : arr) {
            System.out.println(transaction);
        }
        System.out.println();

        System.out.println("Исключение:");
        try {
            tlist.removeTransaction(transaction4.getTid());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    static void print(TransactionsLinkedList list) {
        Transaction l = list.getHead_();
        for (int i = 0; i < list.getSize_(); ++i) {
            System.out.println(l);
            l = l.next;
        }
    }
}
                                                                                                                                                                                                                                                                                                                                  