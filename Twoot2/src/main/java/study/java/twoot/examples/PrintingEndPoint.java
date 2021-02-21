package study.java.twoot.examples;

import study.java.twoot.ReceiverEndPoint;
import study.java.twoot.Twoot;

public class PrintingEndPoint implements ReceiverEndPoint {

    @Override
    public void onTwoot(Twoot twoot) {
        System.out.println(twoot.getSenderId() + " : " + twoot.getContent());
    }
}
