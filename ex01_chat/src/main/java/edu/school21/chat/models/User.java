package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private final Long id_;
    private final String login_;
    private final String password_;
    private List<ChatRoom> createdRooms_;
    private List<ChatRoom> socializingRooms_;

    public User(Long id, String login, String password) {
        id_ = id;
        login_ = login;
        password_ = password;
//        createdRooms_ = createdRooms;
//        socializingRooms_ = socializingRooms;
    }

    void  setCreatedRooms_(List<ChatRoom> createdRooms) {
        createdRooms_ = createdRooms;
    }

    public void setSocializingRooms_(List<ChatRoom> socializingRooms) {
        socializingRooms_ = socializingRooms;
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
        return "User{" +
                "id_=" + id_ +
                ", login_='" + login_ + '\'' +
                ", password_='" + password_ + '\'' +
                ", existsRooms_=" + createdRooms_ +
                ", socializingRooms_=" + socializingRooms_ +
                '}';
    }
}


