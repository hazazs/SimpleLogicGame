package hu.hazazs.slg;

final class Cracker {

	private static Cracker cracker;
	private final PIN generatedPIN;

	private Cracker(PIN generatedPIN) {
		this.generatedPIN = generatedPIN;
	}

	static Cracker getCracker(PIN generatedPIN) {
		if (cracker == null) {
			cracker = new Cracker(generatedPIN);
		}
		return cracker;
	}

	void createInitialMask() {
		createMask(true);
	}

	void createMask(PIN guessPIN) {
		createMask(false, guessPIN);
	}

	private void createMask(boolean initial, PIN... PINs) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < generatedPIN.getDigits().size(); i++) {
			builder.append(initial ? "_" : getDigit(PINs[0], i));
			if (i < generatedPIN.getDigits().size() - 1) {
				builder.append(getRelationalOperator(i));
			}
		}
		System.out.println(builder);
	}

	private String getDigit(PIN guessPIN, int index) {
		int currentGeneratedDigit = generatedPIN.getDigits().get(index);
		int currentGuessDigit = guessPIN.getDigits().get(index);
		if (currentGuessDigit == currentGeneratedDigit) {
			return Color.getColor().green(currentGuessDigit);
		} else if (generatedPIN.getDigits().contains(currentGuessDigit)) {
			return Color.getColor().yellow(currentGuessDigit);
		} else {
			return Color.getColor().red(currentGuessDigit);
		}
	}

	private String getRelationalOperator(int index) {
		int left = generatedPIN.getDigits().get(index);
		int right = generatedPIN.getDigits().get(index + 1);
		if (left > right) {
			return " > ";
		} else {
			return " < ";
		}
	}

}