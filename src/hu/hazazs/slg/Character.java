package hu.hazazs.slg;

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

	@Override
	public String toString() {
		return switch (color) {
			case GREY -> ANSIColor.getColor().grey(value);
			case RED -> ANSIColor.getColor().red(value);
			default -> value;
		};
	}

}