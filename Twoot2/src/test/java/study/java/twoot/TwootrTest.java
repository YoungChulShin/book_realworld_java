package study.java.twoot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.java.twoot.in_memory.InMemoryUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TwootrTest {

    private Twootr twootr;
    private ReceiverEndPoint receiverEndPoint;
    private SenderEndPoint senderEndPoint;

    @BeforeEach
    void setup() {
        receiverEndPoint = mock(ReceiverEndPoint.class);
        twootr = new Twootr(new InMemoryUserRepository());
        twootr.onRegister(TestData.USER_ID, TestData.PASSWORD);
        twootr.onRegister(TestData.OTHER_USER_ID, TestData.OTHER_PASSWORD);
    }

    @Test
    void shouldBeAbleToAuthenticateUser() {
        // 유효 사용자의 로그온 메시지 수신
        // 로그온 메서드는 새 엔드포인트 반환
        // 엔드포인트 유효성을 확인하는 어서션
        logon();
    }

    @Test
    void shouldNotAuthenticateUnknownUser() {
        Optional<SenderEndPoint> senderEndPoint =
                twootr.onLogon(TestData.NOT_A_USER, TestData.PASSWORD, receiverEndPoint);
        Assertions.assertFalse(senderEndPoint.isPresent());
    }

    @Test
    void shouldNotAuthenticateUserWithWrongPassword() {
        Optional<SenderEndPoint> senderEndPoint =
                twootr.onLogon(TestData.USER_ID, TestData.NOT_A_PASSWORD, receiverEndPoint);
        Assertions.assertFalse(senderEndPoint.isPresent());
    }

    @Test
    void shouldFollowValidUser() {
        logon();

        FollowStatus followStatus = senderEndPoint.onFollow(TestData.OTHER_USER_ID);

        assertEquals(FollowStatus.SUCCESS, followStatus);
    }

    @Test
    void shouldNotDuplicateFollowValidUser() {
        logon();

        senderEndPoint.onFollow(TestData.OTHER_USER_ID);
        FollowStatus followStatus = senderEndPoint.onFollow(TestData.OTHER_USER_ID);

        assertEquals(FollowStatus.ALREADY_FOLLOWING, followStatus);
    }

    @Test
    void shouldNotFollowInvalidUser() {
        logon();

        FollowStatus followStatus = senderEndPoint.onFollow(TestData.NOT_A_USER);

        assertEquals(FollowStatus.INVALID_USER, followStatus);
    }

    @Test
    void shouldReceiveTwootsFromFollowedUser() {
        final String id = "1";

        logon();

        senderEndPoint.onFollow(TestData.OTHER_USER_ID);

        SenderEndPoint otherSenderEndPoint = logon(TestData.OTHER_USER_ID,TestData.OTHER_PASSWORD, mock(ReceiverEndPoint.class));
        otherSenderEndPoint.onSendTwoot(id, TestData.TWOOT);

        verify(receiverEndPoint).onTwoot(new Twoot(id, TestData.OTHER_USER_ID, TestData.TWOOT, new Position(0)));
    }

    void logon() {
        this.senderEndPoint = logon(TestData.USER_ID, TestData.PASSWORD, receiverEndPoint);
    }

    SenderEndPoint logon(String userId, String password, ReceiverEndPoint receiverEndPoint) {
        Optional<SenderEndPoint> senderEndPoint = twootr.onLogon(userId, password, receiverEndPoint);
        assertTrue(senderEndPoint.isPresent());
        return senderEndPoint.get();
    }
}