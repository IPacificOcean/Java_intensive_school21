package edu.school21.models;

public class User {
    private long id;
    private String login;
    private String password;
    private boolean authStatus;

    public User(long id, String login, String password, boolean authStatus) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authStatus = authStatus;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAuthStatus() {
        return authStatus;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthStatus(boolean authStatus) {
        this.authStatus = authStatus;
    }
}
