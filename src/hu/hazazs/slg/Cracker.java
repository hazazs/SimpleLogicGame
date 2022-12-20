package hu.hazazs.slg;

final class Cracker {

	private final Safe safe;

	Cracker(Safe safe) {
		this.safe = safe;
		createMask("initial");
	}

	Safe getSafe() {
		return safe;
	}

	private void createMask(String PIN) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < safe.getPIN().length(); i++) {
			builder.append("initial".equals(PIN) ? "_" : getDigit(PIN, i));
			if (i < safe.getPIN().length() - 1) {
				builder.append(getRelationalOperator(i));
			}
		}
		System.out.println(builder);
	}

	private String getDigit(String PIN, int index) {
		if (safe.getPIN().charAt(index) == PIN.charAt(index)) {
			return Color.GREEN + PIN.charAt(index) + Color.RESET;
		} else if (safe.getPIN().contains(String.valueOf(PIN.charAt(index)))) {
			return Color.YELLOW + PIN.charAt(index) + Color.RESET;
		} else {
			return Color.RED + PIN.charAt(index) + Color.RESET;
		}
	}

	private String getRelationalOperator(int index) {
		int left = Integer.parseInt(String.valueOf(safe.getPIN().charAt(index)));
		int right = Integer.parseInt(String.valueOf(safe.getPIN().charAt(index + 1)));
		if (left > right) {
			return " > ";
		} else if (left < right) {
			return " < ";
		} else {
			return " = ";
		}
	}

	boolean checkPIN(String PIN) {
		createMask(PIN);
		return safe.getPIN().equals(PIN);
	}

}