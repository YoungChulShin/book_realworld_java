package study.java;

import java.util.*;

public class Twootr {

    private final UserRepository userRepository;

    public Twootr(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<SenderEndPoint> onLogin(String userId, String password, ReceiverEndPoint receiverEndPoint) {

        Objects.requireNonNull(userId, "userId");
        Objects.requireNonNull(password, "password");

        Optional<User> authenticatedUser = userRepository
                .get(userId)
                .filter(userOfSameId -> {
                    byte[] hashedPassword = KeyGenerator.hash(password, userOfSameId.getSalt());
                    return Arrays.equals(hashedPassword, userOfSameId.getPassword());
                });

        authenticatedUser.ifPresent(user -> {
           user.onLogon(receiverEndPoint);
           
        });

        return authenticatedUser.map(user -> new SenderEndPoint(user, this));
    }

    public RegistrationStatus onRegisterUser(String userId, String password) {
        byte[] salt = KeyGenerator.newSalt();
        byte[] hashedPassword = KeyGenerator.hash(password, salt);
        User user = new User(userId, hashedPassword, salt);

        return userRepository.add(user) ? RegistrationStatus.SUCCESS : RegistrationStatus.DUPLICATE;
    }

    public FollowStatus onFollow(User user, String userIdToFollow) {
        return userRepository.get(userIdToFollow)
                .map(userToFollow -> userRepository.follow(user, userToFollow))
                .orElse(FollowStatus.INVALID_USER);
    }
}
