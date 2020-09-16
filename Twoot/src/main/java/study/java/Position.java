package study.java;

import java.util.Objects;

public class Position {

    public static final Position INITIAL_POSITION = new Position(-1);

    private final int value;

    public Position(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public Position next() {
        return new Position(value + 1);
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {  return true; }

        if (o == null || getClass() != o.getClass()) { return  false; }

        Position position = (Position)o;

        return value == position.getValue();
    }

    @Override
    public String toString() {
        return "Position{" +
                "value=" + value +
                '}';
    }
}
