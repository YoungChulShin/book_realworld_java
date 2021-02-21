package study.java.twoot.in_memory;

import study.java.twoot.FollowStatus;
import study.java.twoot.User;
import study.java.twoot.UserRepository;

import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public Optional<User> get(String userId) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void clear() {

    }

    @Override
    public FollowStatus follow(User follower, User userToFollow) {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
