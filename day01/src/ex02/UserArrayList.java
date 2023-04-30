package ex02;

public class UserArrayList implements UserList {

    @Override
    public void add(User user) {
        if (size_ == capacity_) {
            array_ = reserve();
        }
        array_[size_] = user;
        ++size_;
    }

    @Override
    public User retrieveUserById(int id) {
        for (int i = 0; i < size_; i++) {
            if (array_[i].getId() == id) {
                return array_[i];
            }
        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public User retrieveUserByIndex(int index) {
        return array_[index];
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
        System.arraycopy(array_, 0, tempArr, 0, size_);
        // вариант без arraycopy
//        for (int i = 0; i < size_; i++) {
//            tempArr[i] = array[i];
//        }
        return tempArr;
    }

    private int size_ = 0;
    private int capacity_ = 10;
    private User[] array_ = new User[capacity_];
}
