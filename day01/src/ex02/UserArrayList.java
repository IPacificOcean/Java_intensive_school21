package ex02;

public class UserArrayList implements UserList {

    @Override
    public void add(User user) {
        if (size_ == capacity_) {
            array = reserve();
        }
        array[size_] = user;
        ++size_;
    }

    @Override
    public User retrieveUserById(int id) {
        for (int i = 0; i < size_; i++) {
            if (array[i].getId() == id) {
                return array[i];
            }
        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public User retrieveUserByIndex(int index) {
        return array[index];
    }

    @Override
    public int retrieveNumberOfUsers() {
        return size_;
    }

    public int getCapacity_() {
        return capacity_;
    }

    private User[] reserve() {
        capacity_ *= 2;
        User[] tempArr = new User[capacity_];
        System.arraycopy(array, 0, tempArr, 0, size_);
        return tempArr;
    }

    private int size_ = 0;
    private int capacity_ = 10;
    private User[] array = new User[capacity_];
}
