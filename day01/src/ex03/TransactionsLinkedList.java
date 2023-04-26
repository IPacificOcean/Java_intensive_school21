package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    @Override
    public void addTransaction(Transaction transaction) {
        transaction.next = tail_;
        transaction.prev = null;
        if (tail_ != null) {
            tail_.prev = transaction;
        }
        if (head_ == null) {
            head_ = transaction;
        }

        tail_ = transaction;
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

    private Transaction hasNext() {
        return head_.next;
    }

    private Transaction head_ = null;
    private Transaction tail_ = null;
}


