package hu.hazazs.slg;

import java.util.List;

final class KeyPad {

	private static KeyPad keyPad;
	private final Cracker cracker;
	private final List<Integer> digits = List.of(7, 8, 9, 4, 5, 6, 1, 2, 3, 0);

	private KeyPad(Cracker cracker) {
		this.cracker = cracker;
	}

	static KeyPad getKeyPad(Cracker cracker) {
		if (keyPad == null) {
			keyPad = new KeyPad(cracker);
		}
		return keyPad;
	}

	void showKeyPad() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < digits.size(); i++) {
			builder.append(getDigitWithColor(digits.get(i)));
			if (i % 3 != 2) {
				builder.append(" ");
			} else if (i != 8) {
				builder.append("\n");
			} else {
				builder.append("\n  ");
			}
		}
		System.out.println("\n" + builder);
	}

	private String getDigitWithColor(Integer digit) {
		if (cracker.getGrey().contains(digit)) {
			return Color.getColor().grey(digit);
		} else if (cracker.getGreen().contains(digit)) {
			return Color.getColor().green(digit);
		} else if (cracker.getYellow().contains(digit)) {
			return Color.getColor().yellow(digit);
		} else if (cracker.getRed().contains(digit)) {
			return Color.getColor().red(digit);
		} else {
			return String.valueOf(digit);
		}
	}

}