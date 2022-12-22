package hu.hazazs.slg;

final class Color {

	private static Color color;
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String RED = "\u001B[31m";
	private static final String GREY = "\u001B[37m";
	private static final String RESET = "\u001B[0m";

	private Color() {
	}

	static Color getColor() {
		if (color == null) {
			color = new Color();
		}
		return color;
	}

	String green(Object object) {
		return GREEN + object + RESET;
	}

	String yellow(Object object) {
		return YELLOW + object + RESET;
	}

	String red(Object object) {
		return RED + object + RESET;
	}

	String grey(Object object) {
		return GREY + object + RESET;
	}

	String black(Object object) {
		return RESET + object;
	}

}