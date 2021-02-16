package study.java.twoot;

import java.util.Objects;

public class Twoot {
    private String id;
    private String senderId;
    private String content;

    public Twoot(String id, String senderId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Twoot twoot = (Twoot) o;
        return Objects.equals(id, twoot.id) && Objects.equals(senderId, twoot.senderId) && Objects.equals(content, twoot.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderId, content);
    }
}
