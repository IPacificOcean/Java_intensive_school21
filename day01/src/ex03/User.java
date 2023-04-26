package ex03;


public class User {
    private final int id;
    private String name;
    private long balance;
//    private TransactionsList myTransaction;

    public  User(String name, long balance) {
        if (balance < 0) {
            System.err.println("IllegalArgument balance");
            System.exit(-1);
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
            System.err.println("IllegalArgument balance");
            System.exit(-1);
        }
        this.balance = balance;
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
}
