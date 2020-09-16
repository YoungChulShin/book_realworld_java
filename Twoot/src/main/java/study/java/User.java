package study.java;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class User {

    private String id;
    private byte[] password;
    private byte[] salt;

    private Set<User> followers = new HashSet<>();
    private Set<String> following = new HashSet<>();

    private ReceiverEndPoint receiverEndPoint;

    public User(String id, byte[] password, byte[] salt) {
        this.id = id;
        this.password = password;
        this.salt = salt;
    }

    public void onLogon(ReceiverEndPoint receiverEndPoint) {
        this.receiverEndPoint = receiverEndPoint;
    }

    public FollowStatus addFollower(User user) {
        if (followers.add(user)) {
            user.following.add(id);
            return FollowStatus.SUCCESS;
        } else {
            return FollowStatus.ALREADY_FOLLOWING;
        }
    }

    public String getId() { return id; }

    public byte[] getPassword() { return password; }

    public byte[] getSalt() { return salt; }

    public Stream<User> getFollowers() { return followers.stream(); }

    public boolean isLoggedOn() { return receiverEndPoint != null; }

    public boolean receiveTwoot(Twoot twoot) {
        if (isLoggedOn()) {
            receiverEndPoint.onTwoot(twoot);

            return true;
        } else {
            return false;
        }
    }
}
