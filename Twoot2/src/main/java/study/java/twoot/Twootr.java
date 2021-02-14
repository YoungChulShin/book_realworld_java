package study.java.twoot;

import java.util.*;

public class Twootr {

    private List<String> users = new ArrayList<>();

    public Twootr() {
        users.add("Joe");
    }

    public Optional<SenderEndPoint> onLogon(String userId, ReceiverEndPoint receiver) {

        SenderEndPoint sender = null;
        if (users.contains(userId)) {
            sender = new SenderEndPoint();
        }

        return Optional.ofNullable(sender);
    }
}
