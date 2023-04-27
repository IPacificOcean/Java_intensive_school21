package ex04;


import java.util.UUID;
public class Program {
    public static void main(String[] args) {

        System.out.println("Добавление:");
        TransactionsService tService = new TransactionsService();
        tService.addUser("Mike", 1000);
        tService.addUser("Robert", 2000);
        tService.addUser("Ivan", 3000);
        System.out.println("Кол-во пользователей: " + tService.getUserList_().retrieveNumberOfUsers());
        for (int i = 1; i <= tService.getUserList_().retrieveNumberOfUsers(); ++i) {
            System.out.println("Баланс пользователя id " + i + ": " + tService.retrieveUserBalans(i));
        }
        System.out.println();
        System.out.println("Трансфер:");
        tService.transfer(tService.getUserList_().retrieveUserById(1), tService.getUserList_().retrieveUserById(2), 200);
        tService.transfer(tService.getUserList_().retrieveUserById(2), tService.getUserList_().retrieveUserById(1), 300);
        tService.transfer(tService.getUserList_().retrieveUserById(2), tService.getUserList_().retrieveUserById(1), 500);
        for (int i = 1; i <= tService.getUserList_().retrieveNumberOfUsers(); ++i) {
            System.out.println("Баланс пользователя id " + i + ": " + tService.retrieveUserBalans(i));
        }
        System.out.println();
        System.out.println("Массив трансферов:");
        Transaction[] arr = tService.getTransactions(1);
        for (Transaction a : arr) {
            System.out.println(a);
        }
        System.out.println();
        System.out.println("Массив трансферов пользователя 1 после удаления транзакции:");
        UUID tid = tService.getUserList_().retrieveUserByIndex(1).getMyTransactions().getHead_().getTid();
        tService.removeTransaction(1, tid);
        arr = tService.getTransactions(1);
        for (Transaction a : arr) {
            System.out.println(a);
        }
        System.out.println();
        System.out.println("Массив трансферов пользователя 2 после удаления транзакции у пользователя 1:");
        arr = tService.getTransactions(2);
        for (Transaction a : arr) {
            System.out.println(a);
        }

        System.out.println();
        System.out.println("Исключения:");
        try {
            tService.transfer(tService.getUserList_().retrieveUserById(2), tService.getUserList_().retrieveUserById(1), 5000);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            tService.addUser("Den", -3000);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
                                                                                                                                                                                                                                                                                                                                  