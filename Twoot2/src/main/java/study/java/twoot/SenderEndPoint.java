package study.java.twoot;

import java.util.Objects;

/*
사용자가 코어도메인(twootr)으로 이벤트를 전송하는 엔드포인트 기능
 */
public class SenderEndPoint {
    private User user;
    private Twootr twootr;

    public SenderEndPoint(User user, Twootr twootr) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(twootr, "twootr");

        this.user = user;
        this.twootr = twootr;
    }

    public FollowStatus onFollow(String userIdToFollow) {
        Objects.requireNonNull(userIdToFollow, "userIdToFollow");

        return twootr.onFollow(user, userIdToFollow);
    }

    public void onSendTwoot(String id, String content) {
        final String userId = user.getId();
        final Twoot twoot = new Twoot(id, userId, content);
        user.getFollowers()
                .stream()
                .filter(User::isLoggedOn)
                .forEach(follower -> follower.receiveTwoot(twoot));
    }
}
