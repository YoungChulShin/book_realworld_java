package study.java;

import org.junit.Test;

public class TwootrTest {

    private Twootr twootr;

    @Test
    public void shouldBeAbleToAuthenticateUser() {
        // 유효 사용자의 로그온 메시지 수신

        // 로그온 메서드는 새 엔드포인트 반환
        String userid;
        ReceiverEndPoint receiverEndPoint;

        SenderEnpoint endPoint = twootr.onLogin(userid, receiverEndPoint);

        // 엔드포인트 유효성을 확인하는 어서션
    }
}
