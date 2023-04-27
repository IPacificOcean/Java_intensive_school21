package ex04;


import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction head_ ;
    private Transaction tail_;
    private int size_;

    @Override
    public void addTransaction(Transaction transaction) {
        if (head_ != null) {
            tail_.next = transaction;
            transaction.prev = tail_;
            tail_ = transaction;
        } else {
            head_ = tail_ = transaction;
        }
        ++size_;
    }

    @Override
    public void removeTransaction(UUID id) {
        Transaction l = head_;
        boolean eq = false;
        while (l != null) {
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
                eq = true;
                break;
            }
            l = l.next;
        }
        if (!eq) {
            throw new TransactionNotFoundException("Transaction Not Found");
        }
    }

    @Override
    public Transaction[] toArray() {
       Transaction[] arr = new Transaction[size_];
        Transaction l = head_;
        for (int i = 0; i < size_; ++i) {
            arr[i] = l;
            l = l.next;
        }
        return arr;
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

}


