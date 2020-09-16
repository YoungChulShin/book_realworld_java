package study.java;

import java.util.Objects;

public class SenderEndPoint {

    private final User user;
    private final Twootr twootr;

    SenderEndPoint(User user, Twootr twootr) {

        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(twootr, "twootr");

        this.user = user;
        this.twootr = twootr;
    }

    public FollowStatus onFollow(String userIdToFollow) {
        Objects.requireNonNull(userIdToFollow, "userIdToFollow");

        return twootr.onFollow(user, userIdToFollow);
    }
}
