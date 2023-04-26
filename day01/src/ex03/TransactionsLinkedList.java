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
        Transaction l = head_;

        for (int i = 0; i < size_; ++i) {
//            if (l.next == null && l.prev != null) {
//                l.prev.next = null;
//            }
            if (l.getTid().equals(id)) {
                if (head_ == tail_) {
                    head_ = tail_ = null;
                } else if (l.next == null) {
                    tail_ = l.prev;
                    tail_.next = null;
                } else if (l.prev == null) {
                    head_ = l.next;
                    head_.prev = null;
                } else {
                    l.prev.next = l.next;
                    l.next.prev = l.prev;
                }
                --size_;
                break;
            }
            l = l.next;
        }

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

    private boolean equals(UUID id) {
        boolean eq = false;
        Transaction l = head_;
        for (int i = 0; i < size_; ++i) {
            l = l.next;
            if (l.next.getTid().equals(id)) {
                eq = true;
            }
        }
        return eq;
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


