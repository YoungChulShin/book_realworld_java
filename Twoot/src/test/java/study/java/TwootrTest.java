package study.java;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class TwootrTest {

    private Twootr twootr;

    private final ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);

    @Before
    public void setUp() {
        twootr = new Twootr();
        twootr.onRegisterUser(TestData.USER_ID, TestData.PASSWORD);
    }

    /*
        로그인을 하면
          -> 로그인 이벤트가 발생
          -> SenderEndPoint 객체를 반환한다
     */
    @Test
    public void shouldBeAbleToAuthenticateUser() {
        // 로그온 메서드는 새 엔드포인트 반환
        Optional<SenderEndPoint> endPoint = twootr.onLogin(TestData.USER_ID, TestData.PASSWORD, receiverEndPoint);

        // 엔드포인트 유효성을 확인하는 어서션
        Assertions.assertThat(endPoint.isPresent()).isTrue();
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
}
