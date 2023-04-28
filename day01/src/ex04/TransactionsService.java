package ex04;

import java.util.UUID;

public class TransactionsService {
    private final UserList userList_ = new UserArrayList();

    public void addUser(String name, long balance) {
        User user = new User(name, balance);
        userList_.add(user);
    }

    public long retrieveUserBalans(int id) {
        return userList_.retrieveUserById(id).getBalance();
    }

    public void transfer (User sender, User recipient, long transferAmount) {
        UUID tid = UUID.randomUUID();
        Transaction transaction2 = new Transaction(recipient, sender, -transferAmount, Transaction.transferCategory.credits, tid);
        Transaction transaction1 = new Transaction(recipient, sender, transferAmount, Transaction.transferCategory.debits, tid);

        sender.setBalance(sender.getBalance() - transferAmount);
        sender.addTransaction(transaction2);
        recipient.setBalance(recipient.getBalance() + transferAmount);
        recipient.addTransaction(transaction1);

    }

    public Transaction[] getTransactions (int id) {
        return userList_.retrieveUserById(id).getMyTransactions().toArray();
    }

    public void removeTransaction(int uid, UUID tid) {
        userList_.retrieveUserById(uid).getMyTransactions().removeTransaction(tid);
    }

    public Transaction[] getNotValidTransaction() {
        int NumberOfUsers = userList_.retrieveNumberOfUsers();
        int totalAmountTransactions = 0;
        for (int i = 1; i <= NumberOfUsers; ++i) {
            totalAmountTransactions += userList_.retrieveUserById(i).getMyTransactions().getSize_();
        }

        Transaction[] commonArrayTransactions = new Transaction[totalAmountTransactions];
        Transaction[] notValidTransactions = new Transaction[totalAmountTransactions];
        int index = 0;
        for (int i = 1; i <= NumberOfUsers; ++i) {
            Transaction[] arrUser = getTransactions(i);
            for (Transaction transaction : arrUser) {
                commonArrayTransactions[index] = transaction;
                ++index;
            }
        }

        index = 0;
        for (int i = 0; i < totalAmountTransactions; ++i) {
            int count = 0;
            for (int j = 0; j < totalAmountTransactions; ++j) {
               if (commonArrayTransactions[i].getTid().equals(commonArrayTransactions[j].getTid())) {
                   ++count;
               }
            }
            if (count == 1) {
                notValidTransactions[index] = commonArrayTransactions[i];
                ++index;
            }
        }
        return notValidTransactions;
    }


    public UserList getUserList_() {
        return userList_;
    }
}
