package study.java.twoot;

import java.util.*;

public class Twootr {

    private Map<String, User> users = new HashMap<>();
    private Map<String, User> FollowUsers = new HashMap<>();

    public Optional<SenderEndPoint> onLogon(String userId, String password, ReceiverEndPoint receiver) {
        SenderEndPoint sender = null;
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            byte[] hashedPassword = KeyGenerator.hash(password, user.getSalt());
            if (Arrays.equals(hashedPassword, user.getPassword())) {
                sender = new SenderEndPoint(user, this);
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

    public FollowStatus onFollow(User user, String userIdToFollow) {
        if (users.containsKey(userIdToFollow)) {
            User followUser = users.get(userIdToFollow);
            return followUser.addFollowers(user);
        } else {
            return FollowStatus.INVALID_USER;
        }
    }
}
