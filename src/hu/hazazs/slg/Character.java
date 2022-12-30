package hu.hazazs.slg;

import java.util.List;
import java.util.stream.Collectors;

final class Character {

	private final String value;
	private final Color color;

	Character(Object object, Color color) {
		value = String.valueOf(object);
		this.color = color;
	}

	Character(Object object) {
		value = String.valueOf(object);
		this.color = Color.BLACK;
	}

	Color getColor() {
		return color;
	}

	static int numberOfNotBlackCharacters(List<Character> output) {
		return (int) output.stream().filter(character -> character.getColor() != Color.BLACK).count();
	}

	static String joinCharacters(List<Character> output, String delimeter) {
		return output.stream().map(Character::toString).collect(Collectors.joining(delimeter));
	}

	@Override
	public String toString() {
		return switch (color) {
			case GREY -> ANSIColor.getColor().grey(value);
			case RED -> ANSIColor.getColor().red(value);
			default -> value;
		};
	}

}