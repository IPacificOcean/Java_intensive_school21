package ex00;

import java.util.UUID;

public class Transaction {
    private UUID tid;
    private String recipient;
    private String sender;
    private long transfer_amount;
    enum transferCategory {debits, credits}
}
