package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;


public class Message {
    private final Long id_;
    private final User author_;
    private final ChatRoom room_;
    private final String text_;
    private final Date dateTime_;

    public Message(Long id, User author, ChatRoom room, String text, Date dateTime) {
        id_ = id;
        author_ = author;
        room_ = room;
        text_ = text;
        dateTime_ = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id_.equals(message.id_) && author_.equals(message.author_)
                && room_.equals(message.room_) && Objects.equals(text_, message.text_)
                && dateTime_.equals(message.dateTime_);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_, author_, room_, text_, dateTime_);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id_=" + id_ +
                ", author_=" + author_ +
                ", room_=" + room_ +
                ", text_='" + text_ + '\'' +
                ", dateTime_=" + dateTime_ +
                '}';
    }
}
