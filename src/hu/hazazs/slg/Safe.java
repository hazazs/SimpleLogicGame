package hu.hazazs.slg;

final class Safe {

	private final PIN PIN;

	Safe(PIN PIN) {
		this.PIN = PIN;
		createMask("initial");
	}

	PIN getPIN() {
		return PIN;
	}

	private void createMask(String PIN) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.PIN.getPIN().length(); i++) {
			builder.append("initial".equals(PIN) ? "_" : getDigit(PIN, i));
			if (i < this.PIN.getPIN().length() - 1) {
				builder.append(getRelationalOperator(i));
			}
		}
		System.out.println(builder);
	}

	private String getDigit(String PIN, int index) {
		if (this.PIN.getPIN().charAt(index) == PIN.charAt(index)) {
			return Color.GREEN + PIN.charAt(index) + Color.RESET;
		} else if (this.PIN.getPIN().contains(String.valueOf(PIN.charAt(index)))) {
			return Color.YELLOW + PIN.charAt(index) + Color.RESET;
		} else {
			return Color.RED + PIN.charAt(index) + Color.RESET;
		}
	}

	private String getRelationalOperator(int index) {
		int left = Integer.parseInt(String.valueOf(PIN.getPIN().charAt(index)));
		int right = Integer.parseInt(String.valueOf(PIN.getPIN().charAt(index + 1)));
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
		return this.PIN.getPIN().equals(PIN);
	}

}