package study.java;

public class TestData {

    static final String USER_ID = "Joe";
    static final String PASSWORD = "ahc5ez";

    static final String OTHER_USER_ID = "John";
    static final String OTHER_PASSWORD = "ahc5ek";

    static final String NOT_A_USER = "Jack";
    static final String NOT_A_PASSWORD = "bad password";

    static final String TWOOT = "Hello World!";
    static final String TWOOT_2 = "Bye World!";

    static Twoot twootAt(final String id, final Position position)
    {
        return new Twoot(id, OTHER_USER_ID, TWOOT, position);
    }
}
