package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class ChatRoom {
    private final Long id_;
    private final String name_;
    private final User owner_;
    private final List<Message> messages_;

    public ChatRoom(Long id, String name, User owner, List<Message> messages) {
        id_ = id;
        name_ = name;
        owner_ = owner;
        messages_ = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return id_.equals(chatRoom.id_) && name_.equals(chatRoom.name_)
                && owner_.equals(chatRoom.owner_) && Objects.equals(messages_, chatRoom.messages_);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_, name_, owner_, messages_);
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + id_ +
                ", name='" + name_ + '\'' +
                ", owner=" + owner_ +
                ", messages=" + messages_ +
                '}';
    }
}
