package study.java.twoot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwootQuery {
    private Set<String> inUsers;
    private Position lastSeenPosition;

    public TwootQuery inUsers(final Set<String> inUsers) {
        this.inUsers = inUsers;

        return this;
    }

    public TwootQuery inUsers(String... users) {
        return inUsers(new HashSet<>(Arrays.asList(users)));
    }

    public TwootQuery lastSeenPosition(Position lastSeenPosition) {
        this.lastSeenPosition = lastSeenPosition;

        return this;
    }

    public boolean hasUsers() {
        return inUsers != null && !inUsers.isEmpty();
    }


}
