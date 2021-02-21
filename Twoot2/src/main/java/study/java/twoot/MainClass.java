package study.java.twoot;

import java.awt.*;

public class MainClass {

    public static void main(String... args) {
        ReceiverEndPoint lamda = twoot -> System.out.println(twoot.getId() + ": " + twoot.getContent());
    }
}
