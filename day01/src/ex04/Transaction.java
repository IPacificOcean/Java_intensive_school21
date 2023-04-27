package ex04;

import java.util.UUID;

public class Transaction {
    private UUID tid;
    private User recipient;
    private User sender;
    private long transfer_amount;
    private transferCategory category;
    Transaction next;
    Transaction prev;
    enum transferCategory {debits, credits}

    Transaction(User recipient, User sender, long transfer_amount, transferCategory category) {
        if (category.equals(transferCategory.credits) && transfer_amount >= 0){
            System.err.println("IllegalArgument transfer_amount");
            System.exit(-1);
        }
        if (category.equals(transferCategory.debits) && transfer_amount < 0){
            System.err.println("IllegalArgument transfer_amount");
            System.exit(-1);
        }

        this.tid = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transfer_amount = transfer_amount;
        this.category = category;
    }

    Transaction(User recipient, User sender, long transfer_amount, transferCategory category, UUID tid) {
        if (category.equals(transferCategory.credits) && transfer_amount >= 0){
            System.err.println("IllegalArgument transfer_amount");
            System.exit(-1);
        }
        if (category.equals(transferCategory.debits) && transfer_amount < 0){
            System.err.println("IllegalArgument transfer_amount");
            System.exit(-1);
        }

        this.tid = tid;
        this.recipient = recipient;
        this.sender = sender;
        this.transfer_amount = transfer_amount;
        this.category = category;
    }

    public void setTid(UUID tid) {
        this.tid = tid;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTransfer_amount(long transfer_amount) {
        if (category.equals(transferCategory.credits) && transfer_amount >= 0){
            System.err.println("IllegalArgument transfer_amount");
            System.exit(-1);
        }
        if (category.equals(transferCategory.debits) && transfer_amount < 0){
            System.err.println("IllegalArgument transfer_amount");
            System.exit(-1);
        }
        this.transfer_amount = transfer_amount;
    }

    public void setCategory(transferCategory category) {
        this.category = category;
    }

    public UUID getTid() {
        return tid;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public long getTransfer_amount() {
        return transfer_amount;
    }

    public transferCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "tid: " + getTid() + ", recipient: " + getRecipient() + ", sender: " + getSender() + ", transfer_amount: " + getTransfer_amount() + ", transferCategory: " + getCategory();
    }

}
