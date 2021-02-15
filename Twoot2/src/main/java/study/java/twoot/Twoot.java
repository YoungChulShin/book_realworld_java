package study.java.twoot;

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
}
