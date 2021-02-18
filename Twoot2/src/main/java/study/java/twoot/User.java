package study.java.twoot;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;
    private byte[] password;
    private byte[] salt;
    private Set<String> following = new HashSet<>();
    private Set<User> followers = new HashSet<>();

    private Position lastSeenPosition;
    private ReceiverEndPoint receiverEndPoint;

    public User(String id, byte[] password, byte[] salt, Position lastSeenPosition) {
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.lastSeenPosition = lastSeenPosition;
    }

    public void onLogon(ReceiverEndPoint receiverEndPoint) {
        this.receiverEndPoint = receiverEndPoint;
    }

    public FollowStatus addFollowers(User user) {
        if (followers.add(user)) {
            user.following.add(id);
            return FollowStatus.SUCCESS;
        } else {
            return FollowStatus.ALREADY_FOLLOWING;
        }
    }

    public boolean isLoggedOn() {
        return receiverEndPoint != null;
    }

    public boolean receiveTwoot(Twoot twoot) {
        if (isLoggedOn()) {
            receiverEndPoint.onTwoot(twoot);
            lastSeenPosition = twoot.getPosition();
            return true;
        } else {
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public Position getLastSeenPosition() {
        return lastSeenPosition;
    }
}
