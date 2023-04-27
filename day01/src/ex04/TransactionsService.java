package ex04;

import java.util.UUID;

public class TransactionsService {
    private  UserList userList_ = new UserArrayList();

    public void addUser(String name, long balance) {
        User user = new User(name, balance);
        userList_.add(user);
    }

    public long retrieveUserBalans(int id) {
        return userList_.retrieveUserById(id).getBalance();
    }

    public void transfer (User sender, User recipient, long transferAmount) {
        UUID tid = UUID.randomUUID();
        Transaction transaction2 = new Transaction(sender, recipient, -transferAmount, Transaction.transferCategory.credits, tid);
        Transaction transaction1 = new Transaction(recipient, sender, transferAmount, Transaction.transferCategory.debits, tid);

        sender.setBalance(sender.getBalance() - transferAmount);
        sender.addTransaction(transaction2);
        recipient.setBalance(recipient.getBalance() + transferAmount);
        recipient.addTransaction(transaction1);

    }

    public Transaction[] getTransactions (int id) {
        return userList_.retrieveUserById(id).getMyTransaction().toArray();
    }

    public UserList getUserList_() {
        return userList_;
    }
}
