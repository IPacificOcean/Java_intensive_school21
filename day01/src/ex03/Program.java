package ex03;


import static ex03.Transaction.transferCategory.credits;

public class Program {
    public static void main(String[] args) {
        User user = new User("Mike", 1000);
        User user2 = new User("Robert", 2000);
        User user3 = new User("Ivan", 3000);

        int transfer_amount = -500;
        Transaction transaction1 = new Transaction(user, user2, transfer_amount, credits);





//        try {
//            arr.retrieveUserById(11);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
                                                                                                                                                                                                                                                                                                                                  