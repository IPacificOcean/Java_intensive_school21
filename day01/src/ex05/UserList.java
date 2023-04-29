package ex05;


public interface UserList {
    void add(User user);

    User retrieveUserById(int id) throws UserNotFoundException;

    User retrieveUserByIndex(int index);

    int retrieveNumberOfUsers();
}
