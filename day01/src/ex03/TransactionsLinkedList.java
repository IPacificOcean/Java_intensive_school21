package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    @Override
    public void addTransaction(Transaction transaction) {

        if (head_ != null) {
            head_.next = transaction;
            transaction.prev= head_;
        }
        head_ = transaction;
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
}


