package study.java.twoot;

public class User {
    private String id;
    private byte[] password;
    private byte[] salt;

    public User(String id, byte[] password, byte[] salt) {
        this.id = id;
        this.password = password;
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }
}
