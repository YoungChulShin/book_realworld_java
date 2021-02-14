package study.java.twoot;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;
    private byte[] password;
    private byte[] salt;

    private Set<String> following = new HashSet<>();
    private Set<User> followers = new HashSet<>();

    public User(String id, byte[] password, byte[] salt) {
        this.id = id;
        this.password = password;
        this.salt = salt;
    }

    public FollowStatus addFollowers(User user) {
        if (followers.add(user)) {
            user.following.add(id);
            return FollowStatus.SUCCESS;
        } else {
            return FollowStatus.ALREADY_FOLLOWING;
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
}
