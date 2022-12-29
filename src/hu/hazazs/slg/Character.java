package hu.hazazs.slg;

final class Character {

	private final String value;
	private final boolean invalid;

	Character(Object object, boolean invalid) {
		value = String.valueOf(object);
		this.invalid = invalid;
	}

	boolean isInvalid() {
		return invalid;
	}

	@Override
	public String toString() {
		return invalid ? ANSIColor.getColor().red(value) : ANSIColor.getColor().black(value);
	}

}