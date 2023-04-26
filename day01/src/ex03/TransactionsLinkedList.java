package ex03;

import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    @Override
    public void addTransaction(Transaction transaction) {
        transaction.next = null;
        transaction.prev = tail_;
        if (tail_ != null) {
            tail_.next = transaction;
        }
        if (head_ == null) {
            head_ = transaction;
        }

        tail_ = transaction;
        ++size_;
    }

    @Override
    public void removeTransaction(UUID id) {

    }

    @Override
    public Transaction[] toArray() {
        return new Transaction[0];
    }

    public Transaction getHead_() {
        return head_;
    }

    public Transaction getTail_() {
        return tail_;
    }

    public int getSize_() {
        return size_;
    }

    public Transaction hasNext() {
        Transaction transaction = head_;
            transaction = transaction.next;
        return transaction;
    }

//    if (index < (size >> 1)) {
//        LinkedList.Node<E> x = first;
//        for (int i = 0; i < index; i++)
//            x = x.next;
//        return x;
//    } else {
//        LinkedList.Node<E> x = last;
//        for (int i = size - 1; i > index; i--)
//            x = x.prev;
//        return x;
//    }

    private Transaction head_ = null;
    private Transaction tail_ = null;
    private int size_ = 0;
}


