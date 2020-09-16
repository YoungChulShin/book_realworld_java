package study.java;

import java.util.Objects;

public class Twoot {

    private String id;
    private String senderId;
    private String content;
    private Position position;

    public Twoot(String id, String senderId, String content, Position position) {

        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(senderId, "senderId");
        Objects.requireNonNull(content, "content");
        Objects.requireNonNull(position, "position");

        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public Position getPosition() {
        return position;
    }
}
