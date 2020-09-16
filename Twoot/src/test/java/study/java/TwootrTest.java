package study.java;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import study.java.in_memory.InMemoryUserRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class TwootrTest {

    private final ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);
    private Twootr twootr;

    private SenderEndPoint senderEndPoint;

    private final UserRepository userRepository = new InMemoryUserRepository();

    @Before
    public void setUp() {
        twootr = new Twootr(userRepository);
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

    private void logon() {
        this.senderEndPoint = logon(TestData.USER_ID, receiverEndPoint);
    }

    private SenderEndPoint logon(String userId, ReceiverEndPoint receiverEndPoint) {

        Optional<SenderEndPoint> endPoint = twootr.onLogin(userId, TestData.PASSWORD, receiverEndPoint);
        Assertions.assertThat(endPoint.isPresent()).isTrue();
        return endPoint.get();
    }
}
