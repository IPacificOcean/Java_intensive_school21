package ex02;


public class UserIdsGenerator {
    static int i = 0;
    private static final UserIdsGenerator INSTANCE = new UserIdsGenerator();

    private UserIdsGenerator() {}

    int generateId() {
        return ++i;
    }

    public static UserIdsGenerator getInstance() {
        return INSTANCE;
    }
}
