package study.java;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import study.java.in_memory.InMemoryTwootRepository;
import study.java.in_memory.InMemoryUserRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static study.java.TestData.twootAt;

public class TwootrTest {

    private static final Position POSITION_1 = new Position(0);

    private final ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);
    private Twootr twootr;

    private SenderEndPoint senderEndPoint;

    private final UserRepository userRepository = new InMemoryUserRepository();
    private final TwootRepository twootRepository = new InMemoryTwootRepository();

    @Before
    public void setUp() {
        twootr = new Twootr(userRepository, twootRepository);
        twootr.onRegisterUser(TestData.USER_ID, TestData.PASSWORD);
        twootr.onRegisterUser(TestData.OTHER_USER_ID, TestData.OTHER_PASSWORD);
    }

    /*
        로그인을 하면
          -> 로그인 이벤트가 발생
          -> SenderEndPoint 객체를 반환한다
     */
    @Test
    public void shouldBeAbleToAuthenticateUser() {
        logon();
    }

    @Test
    public void shouldNotAuthenticateUnknownUser() {
        // 로그온 메서드는 새 엔드포인트 반환
        Optional<SenderEndPoint> endPoint = twootr.onLogin(TestData.NOT_A_USER, TestData.PASSWORD, receiverEndPoint);

        // 엔드포인트 유효성을 확인하는 어서션
        Assertions.assertThat(endPoint.isPresent()).isFalse();
    }

    @Test
    public void shouldNotAuthenticateUserWithWrongPassword() {
        // 로그온 메서드는 새 엔드포인트 반환
        Optional<SenderEndPoint> endPoint = twootr.onLogin(TestData.USER_ID, TestData.NOT_A_PASSWORD, receiverEndPoint);

        // 엔드포인트 유효성을 확인하는 어서션
        Assertions.assertThat(endPoint.isPresent()).isFalse();
    }

    @Test
    public void shouldFollowValidUser() {
        logon();

        FollowStatus followStatus = senderEndPoint.onFollow(TestData.OTHER_USER_ID);

        Assertions.assertThat(followStatus).isEqualTo(FollowStatus.SUCCESS);
    }

    @Test
    public void shouldNotDuplicateFollowValidUser() {
        logon();

        FollowStatus followStatus = senderEndPoint.onFollow(TestData.OTHER_USER_ID);
        FollowStatus sencondFollowStatus = senderEndPoint.onFollow(TestData.OTHER_USER_ID);

        Assertions.assertThat(sencondFollowStatus).isEqualTo(FollowStatus.ALREADY_FOLLOWING);
    }

    @Test
    public void shouldNotFollowInvalidUser() {

        logon();

        FollowStatus followStatus = senderEndPoint.onFollow(TestData.NOT_A_USER);

        Assertions.assertThat(followStatus).isEqualTo(FollowStatus.INVALID_USER);
    }

    @Test
    public void shouldReceiveTwootsFromFollowedUser() {
        String id = "1";

        logon();

        senderEndPoint.onFollow(TestData.OTHER_USER_ID);

        SenderEndPoint otherSenderEndPoint = otherLogon();
        otherSenderEndPoint.onSendTwoot(id, TestData.TWOOT);

        Mockito.verify(twootRepository).add(id, TestData.OTHER_USER_ID, TestData.TWOOT);
        Mockito.verify(receiverEndPoint).onTwoot(new Twoot(id, TestData.OTHER_USER_ID, TestData.TWOOT, new Position(0)));
    }

    @Test
    public void shouldReceiveReplayOfTwootsAfterLogoff() {
        String id = "1";

        userFollowsOtherUser();

        SenderEndPoint otherSenderEndPoint = otherLogon();
        otherSenderEndPoint.onSendTwoot(id, TestData.TWOOT);

        logon();

        Mockito.verify(receiverEndPoint).onTwoot(twootAt(id, POSITION_1));
    }

    private void logon() {
        this.senderEndPoint = logon(TestData.USER_ID, receiverEndPoint);
    }

    private SenderEndPoint logon(String userId, ReceiverEndPoint receiverEndPoint) {

        Optional<SenderEndPoint> endPoint = twootr.onLogin(userId, TestData.PASSWORD, receiverEndPoint);
        Assertions.assertThat(endPoint.isPresent()).isTrue();
        return endPoint.get();
    }

    private SenderEndPoint otherLogon() {
        return logon(TestData.OTHER_USER_ID, mock(ReceiverEndPoint.class));
    }

    private void userFollowsOtherUser() {
        logon();

        senderEndPoint.onFollow(TestData.OTHER_USER_ID);

        senderEndPoint.onLogoff();
    }
}
