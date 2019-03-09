package monster;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Type {
	FIRE("FIRE", "WATER"),
	WATER("WATER", "GRASS"),
	ELECTRIC("ELECTRIC", "GRASS"),
	GRASS("FIRE", "GRASS");
	
	private Set<String> weaknesses;
	
	Type(String... weakStr) {
		weaknesses = Arrays.asList(weakStr).stream().collect(Collectors.toSet());
	}
	
	public boolean isWeakAgainst(Type type) {
		return weaknesses.contains(type.name());
	}
}
