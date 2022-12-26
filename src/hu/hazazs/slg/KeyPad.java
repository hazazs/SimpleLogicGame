package hu.hazazs.slg;

import java.util.HashMap;
import java.util.Map;

final class KeyPad {

	private static KeyPad keyPad;
	private final Map<Integer, Digit> digits = new HashMap<>();
	private final String[][] pad;

	private KeyPad() {
		digits.put(7, new Digit(7, new Coordinate(0, 0)));
		digits.put(8, new Digit(8, new Coordinate(0, 2)));
		digits.put(9, new Digit(9, new Coordinate(0, 4)));
		digits.put(4, new Digit(4, new Coordinate(1, 0)));
		digits.put(5, new Digit(5, new Coordinate(1, 2)));
		digits.put(6, new Digit(6, new Coordinate(1, 4)));
		digits.put(1, new Digit(1, new Coordinate(2, 0)));
		digits.put(2, new Digit(2, new Coordinate(2, 2)));
		digits.put(3, new Digit(3, new Coordinate(2, 4)));
		digits.put(0, new Digit(0, new Coordinate(3, 2)));
		pad = new String[getRowNumber()][getColumnNumber()];
		clearPad();
	}

	static KeyPad getKeyPad() {
		if (keyPad == null) {
			keyPad = new KeyPad();
		}
		return keyPad;
	}

	Map<Integer, Digit> getDigits() {
		return digits;
	}

	private int getRowNumber() {
		return digits.values().stream().mapToInt(digit -> digit.getCoordinate().getRow()).max().orElse(0) + 1;
	}

	private int getColumnNumber() {
		return digits.values().stream().mapToInt(digit -> digit.getCoordinate().getColumn()).max().orElse(0) + 1;
	}

	private void clearPad() {
		for (int row = 0; row < pad.length; row++) {
			for (int column = 0; column < pad[row].length; column++) {
				pad[row][column] = " ";
			}
		}
	}

	private void updatePad() {
		for (Digit digit : digits.values()) {
			String value = switch (digit.getColor()) {
				case BLACK -> String.valueOf(digit.getValue());
				case GREY -> ANSIColor.getColor().grey(digit.getValue());
				case GREEN -> ANSIColor.getColor().green(digit.getValue());
				case YELLOW -> ANSIColor.getColor().yellow(digit.getValue());
				case RED -> ANSIColor.getColor().red(digit.getValue());
			};
			pad[digit.getCoordinate().getRow()][digit.getCoordinate().getColumn()] = value;
		}
	}

	@Override
	public String toString() {
		updatePad();
		StringBuilder pad = new StringBuilder();
		for (int row = 0; row < this.pad.length; row++) {
			for (int column = 0; column < this.pad[row].length; column++) {
				pad.append(this.pad[row][column]);
			}
			pad.append("\n");
		}
		return pad.toString();
	}

}