package ex02;

public interface UserList {
    void add(User user);
    User retrieveUserById (int id);
    User retrieveUserByIndex (int index);
    User retrieveNumberOfUsers (int num);

}
