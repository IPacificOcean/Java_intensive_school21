package edu.school21.chat.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class User {
    private final Long id_;
    private final String login_;
    private final String password_;
    private final List<ChatRoom> createdRooms_;
    private final List<ChatRoom> socializingRooms_;

    public User(Long id, String login, String password, List<ChatRoom> createdRooms, List<ChatRoom> socializingRooms) {
        id_ = id;
        login_ = login;
        password_ = password;
        createdRooms_ = createdRooms;
        socializingRooms_ = socializingRooms;
    }

    public Long getId() {
        return id_;
    }

    public String getLogin() {
        return login_;
    }

    public String getPassword() {
        return password_;
    }

    public List<ChatRoom> getCreatedRooms() {
        return createdRooms_;
    }

    public List<ChatRoom> getSocializingRooms() {
        return socializingRooms_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id_.equals(user.id_) && login_.equals(user.login_)
                && password_.equals(user.password_) && Objects.equals(createdRooms_, user.createdRooms_)
                && Objects.equals(socializingRooms_, user.socializingRooms_);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_, login_, password_, createdRooms_, socializingRooms_);
    }

    @Override
    public String toString() {
        return "User{\n" +
                "id=" + id_ +
                ", \nlogin='" + login_ + '\'' +
                ", \npassword='" + password_ + '\'' +
                ", \ncreatedRooms=" + createdRooms_ +
                ", \nsocializingRooms=" + socializingRooms_ +
                '}';
    }


}


