package study.java.twoot;

import java.util.*;

public class Twootr {

    private Map<String, User> users = new HashMap<>();

    public Optional<SenderEndPoint> onLogon(String userId, String password, ReceiverEndPoint receiver) {
        SenderEndPoint sender = null;
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            byte[] hashedPassword = KeyGenerator.hash(password, user.getSalt());
            if (Arrays.equals(hashedPassword, user.getPassword())) {
                sender = new SenderEndPoint();
            }
        }

        return Optional.ofNullable(sender);
    }

    public RegistrationStatus onRegister(String userId, String password) {
        byte[] salt = KeyGenerator.newSalt();
        byte[] hashedPassword = KeyGenerator.hash(password, salt);
        User user = new User(userId, hashedPassword, salt);

        if (users.containsKey(userId)) {
            return RegistrationStatus.DUPLICATE;
        } else {
            users.put(userId, user);
            return RegistrationStatus.SUCCESS;
        }
    }

}
