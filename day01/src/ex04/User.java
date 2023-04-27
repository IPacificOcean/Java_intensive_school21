package ex04;


public class User {
    private final int id;
    private String name;
    private long balance;
    private TransactionsLinkedList myTransactions = new TransactionsLinkedList();

    public  User(String name, long balance) {
        if (balance < 0) {
            throw new IllegalTransactionException("Illegal Transaction Exception");
        }
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "id: " + getId() + ", name: " + getName() + ", balans: " + getBalance();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(long balance) {
        if (balance < 0) {
            throw new IllegalTransactionException("Illegal Transaction Exception");
        }
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        myTransactions.addTransaction(transaction);
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public long getBalance() {
        return balance;
    }

    public TransactionsLinkedList getMyTransactions() {
        return myTransactions;
    }
}
