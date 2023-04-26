package ex03;


import java.util.LinkedList;

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
        TransactionsLinkedList tlist = new TransactionsLinkedList();
        tlist.addTransaction(transaction1);
        tlist.addTransaction(transaction2);
        tlist.addTransaction(transaction3);
//        System.out.println(tlist.getHead_().prev.prev);
        System.out.println(tlist.getHead_().toString());
        System.out.println(tlist.getHead_().next.toString());
        System.out.println(tlist.getHead_().next.next.toString());
        System.out.println(tlist.getTail_().prev.toString());
//        LinkedList<String> list = new LinkedList<String>();
        System.out.println(tlist.getSize_());
        System.out.println();
        Transaction l = tlist.getHead_();
        for (int i = 0; i < tlist.getSize_(); ++i) {
            System.out.println(l);
            l = l.next;
        }




//        try {
//            arr.retrieveUserById(11);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
                                                                                                                                                                                                                                                                                                                                  