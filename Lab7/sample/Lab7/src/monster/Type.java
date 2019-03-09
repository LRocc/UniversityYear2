package monster;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Type {
    FIRE("FIRE", "WATER"),
    WATER("WATER", "GRASS"),
    ELECTRIC("ELECTRIC", "GRASS"),
    GRASS("FIRE", "GRASS");

    public Set<String> weak;

    Type(String... weak) {
        this.weak = new HashSet<>(Arrays.asList(weak));

    }

    public boolean isWeakAgainst(Type otherType) {
        return weak.contains(otherType.name());

    }
}
