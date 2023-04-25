package ex03;


public class Program {
    public static void main(String[] args) {
        User user = new User("Mike", 1000);
        User user2 = new User("Robert", 2000);
        User user3 = new User("Ivan", 3000);

        UserArrayList arr = new UserArrayList();
        arr.add(user);
        arr.add(user2);
        arr.add(user3);

        System.out.println(arr.retrieveUserByIndex(0));
        System.out.println(arr.retrieveUserByIndex(1));
        System.out.println(arr.retrieveUserByIndex(2));
        System.out.println();
        System.out.println(arr.retrieveUserById(1));
        System.out.println();
        System.out.println("Number 0f users: " + arr.retrieveNumberOfUsers());
        System.out.println();

        try {
            arr.retrieveUserById(11);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
                                                                                                                                                                                                                                                                                                                                  