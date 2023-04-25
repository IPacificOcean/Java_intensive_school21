package ex01;

public class Program {
    public static void main(String[] args) {
        User user = new User("Mike", 1000);
        User user2 = new User("Robert", 2000);
        User user3 = new User("Ivan", 3000);

        System.out.println("id: " + user.getId() + ", name: " + user.getName() + ", balans: " + user.getBalance());
        System.out.println("id: " + user2.getId() + ", name: " + user2.getName() + ", balans: " + user2.getBalance());
        System.out.println("id: " + user3.getId() + ", name: " + user3.getName() + ", balans: " + user3.getBalance());

    }
}
