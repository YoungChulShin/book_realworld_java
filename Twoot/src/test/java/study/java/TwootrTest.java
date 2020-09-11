package study.java;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TwootrTest {

    private Twootr twootr = new Twootr();

    private final ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);

    /*
        로그인을 하면
          -> 로그인 이벤트가 발생
          -> SenderEndPoint 객체를 반환한다
     */
    @Test
    public void shouldBeAbleToAuthenticateUser() {
        // 유효 사용자의 로그온 메시지 수신

        // 로그온 메서드는 새 엔드포인트 반환
        String userid = "";

            SenderEndPoint endPoint = twootr.onLogin(userid, receiverEndPoint);

        // 엔드포인트 유효성을 확인하는 어서션
    }

    @Test
    public void shouldNotAuthenticateUnknownUser() {
        // 유효 사용자의 로그온 메시지 수신

        // 로그온 메서드는 새 엔드포인트 반환
        String userid = "";

        SenderEndPoint endPoint = twootr.onLogin(userid, receiverEndPoint);

        // 엔드포인트 유효성을 확인하는 어서션
    }
}
