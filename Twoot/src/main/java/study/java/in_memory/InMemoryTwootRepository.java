package study.java.in_memory;

import study.java.Position;
import study.java.Twoot;
import study.java.TwootQuery;
import study.java.TwootRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class InMemoryTwootRepository implements TwootRepository {

    private final List<Twoot> twoots = new ArrayList<>();

    private Position currentPosition = Position.INITIAL_POSITION;

    @Override
    public Twoot add(String id, String userId, String content) {
        return null;
    }

    @Override
    public Optional<Twoot> get(String id) {
        return Optional.empty();
    }

    @Override
    public void delete(Twoot twoot) {

    }

    @Override
    public void query(TwootQuery twootQuery, Consumer<Twoot> callback) {

    }

    @Override
    public void clear() {

    }
}
