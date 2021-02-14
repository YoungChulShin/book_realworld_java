package study.java.twoot;

public class SenderEndPoint {
    private User user;
    private Twootr twootr;

    public SenderEndPoint(User user, Twootr twootr) {
        this.user = user;
        this.twootr = twootr;
    }

    public FollowStatus onFollow(String userIdToFollow) {
        return twootr.onFollow(user, userIdToFollow);
    }
}
