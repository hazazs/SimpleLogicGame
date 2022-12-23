package hu.hazazs.slg;

final class Color {

	private static Color color;
	private static final String RESET = "\033[0m";
	private static final String GREY = "\033[1;37m";
	private static final String GREEN = "\033[1;32m";
	private static final String YELLOW = "\033[1;33m";
	private static final String RED = "\033[1;31m";

	private Color() {
	}

	static Color getColor() {
		if (color == null) {
			color = new Color();
		}
		return color;
	}

	String black(Object object) {
		return RESET + object;
	}

	String grey(Object object) {
		return GREY + object + RESET;
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

}