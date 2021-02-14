package study.java.twoot;

import java.util.*;

public class Twootr {

    private Map<String, User> users = new HashMap<>();

    public Twootr() {
        users.put("Joe", new User("Joe", "ahc5ez"));
    }

    public Optional<SenderEndPoint> onLogon(String userId, String password, ReceiverEndPoint receiver) {
        SenderEndPoint sender = null;
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            if (user.getPassword().equals(password)) {
                sender = new SenderEndPoint();
            }
        }

        return Optional.ofNullable(sender);
    }


}
