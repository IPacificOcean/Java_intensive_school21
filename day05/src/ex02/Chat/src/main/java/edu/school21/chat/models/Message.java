package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Message {
    private Long id_;
    private final User author_;
    private final ChatRoom room_;
    private final String text_;
    private final LocalDateTime dateTime_;
    private final DateTimeFormatter dateTimeFormat_ = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"); // todo format in previews day

    public Message(Long id, User author, ChatRoom room, String text, LocalDateTime dateTime) {
        id_ = id;
        author_ = author;
        room_ = room;
        text_ = text;
        dateTime_ = dateTime;
    }

    public Long getId() {
        return id_;
    }

    public User getAuthor() {
        return author_;
    }

    public ChatRoom getRoom() {
        return room_;
    }

    public String getText() {
        return text_;
    }

    public LocalDateTime getDateTime() {
        return dateTime_;
    }

    public void setId(Long id) {
        this.id_ = id;
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
        return "Message : {\n" +
                "id = " + id_ +
                ", \nauthor = " + author_ +
                ", \nroom = " + room_ +
                ", \ntext = '" + text_ + '\'' +
                ", \ndateTime = " + dateTime_.format(dateTimeFormat_) +
                '}';
    }
}
