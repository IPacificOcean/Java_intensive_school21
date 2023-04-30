package ex01.ex00;

public class Program {
    public static void main(String[] args) {
        User user_1 = new User(1, "Ivan", 1000);
        User user_2 = new User(2, "Robert", 3000);

        System.out.println("Данные до транзакции:");
        System.out.println("id: " + user_1.getUid() + ", name: " + user_1.getName() + ", balans: " + user_1.getBalance());
        System.out.println("id: " + user_2.getUid() + ", name: " + user_2.getName() + ", balans: " + user_2.getBalance());
        System.out.println();
        int transfer_amount = -500;
        Transaction transaction = new Transaction(user_2, user_1, transfer_amount, Transaction.transferCategory.credits);

        System.out.println("Данные после транзакции credits -500: " + transaction.getTid());
        user_1.setBalance(user_1.getBalance() + transfer_amount);
        System.out.println("id: " + user_1.getUid() + ", name: " + user_1.getName() + ", balans: " + user_1.getBalance());

        user_2.setBalance(user_2.getBalance() - transfer_amount);
        System.out.println("id: " + user_2.getUid() + ", name: " + user_2.getName() + ", balans: " + user_2.getBalance());

        transfer_amount = 1000;
        Transaction transaction2 = new Transaction(user_2, user_1, transfer_amount, Transaction.transferCategory.debits);
        System.out.println();
        System.out.println("Данные после транзакции debits 1000: " + transaction2.getTid());
        user_2.setBalance(user_2.getBalance() - transfer_amount);
        System.out.println("id: " + user_2.getUid() + ", name: " + user_2.getName() + ", balans: " + user_2.getBalance());

        user_1.setBalance(user_1.getBalance() + transfer_amount);
        System.out.println("id: " + user_1.getUid() + ", name: " + user_1.getName() + ", balans: " + user_1.getBalance());

    }
}
