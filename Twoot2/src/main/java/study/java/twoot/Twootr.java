package study.java.twoot;

import study.java.twoot.in_memory.InMemoryUserRepository;

import java.util.*;

public class Twootr {

    private Map<String, User> users = new HashMap<>();
    private Map<String, User> FollowUsers = new HashMap<>();
    private final UserRepository userRepository;

    public Twootr(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<SenderEndPoint> onLogon(String userId, String password, ReceiverEndPoint receiver) {
        SenderEndPoint sender = null;
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            byte[] hashedPassword = KeyGenerator.hash(password, user.getSalt());
            if (Arrays.equals(hashedPassword, user.getPassword())) {
                user.onLogon(receiver);
                sender = new SenderEndPoint(user, this);
            }
        }

        return Optional.ofNullable(sender);
    }

    public Optional<SenderEndPoint> onLogon2(String userId, String password, ReceiverEndPoint receiver) {
        Optional<User> authenticatedUser = userRepository
                .get(userId)
                .filter(userOfSameId ->
                {
                    byte[] hashedPassword = KeyGenerator.hash(password, userOfSameId.getSalt());
                    return Arrays.equals(hashedPassword, userOfSameId.getPassword());
                });

        authenticatedUser.ifPresent(user -> {
            user.onLogon(receiver);
            // do something
        });

        return authenticatedUser.map(user -> new SenderEndPoint(user, this));
    }

    public RegistrationStatus onRegister(String userId, String password) {
        byte[] salt = KeyGenerator.newSalt();
        byte[] hashedPassword = KeyGenerator.hash(password, salt);
        User user = new User(userId, hashedPassword, salt, Position.INITIAL_POSITION);

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

    public Position onSendTwoot(String id, User user, String content) {
        final String userId = user.getId();
        // todo - position 정보 확인 필요
        final Twoot twoot = new Twoot(id, userId, content, Position.INITIAL_POSITION);
        user.getFollowers()
                .stream()
                .filter(User::isLoggedOn)
                .forEach(follower -> follower.receiveTwoot(twoot));


        return twoot.getPosition();
    }


}
