package study.java;

import java.util.*;

public class Twootr {

    private Map<String, User> userList = new HashMap<>();

    public Optional<SenderEndPoint> onLogin(String userId, String password, ReceiverEndPoint receiverEndPoint) {

        if (!userList.containsKey(userId)) {
            return Optional.empty();
        }

        User userOfSameId = userList.get(userId);
        byte[] hashedPassword = KeyGenerator.hash(password, userOfSameId.getSalt());



        if (!Arrays.equals(userOfSameId.getPassword(),hashedPassword)) {
            return Optional.empty();
        }

        return Optional.of(new SenderEndPoint());
    }

    public RegistrationStatus onRegisterUser(String userId, String password) {
        if (userList.containsKey(userId)) {
            return RegistrationStatus.DUPLICATE;
        }

        byte[] salt = KeyGenerator.newSalt();
        byte[] hashedPassword = KeyGenerator.hash(password, salt);
        User user = new User(userId, hashedPassword, salt);

        userList.put(userId, user);
        return RegistrationStatus.SUCCESS;
    }
}
