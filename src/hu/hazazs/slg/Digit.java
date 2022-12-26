package hu.hazazs.slg;

final class Digit {

	private final int value;
	private final Coordinate coordinate;
	private Color color = Color.BLACK;

	Digit(int value, Coordinate coordinate) {
		this.value = value;
		this.coordinate = coordinate;
	}

	int getValue() {
		return value;
	}

	Coordinate getCoordinate() {
		return coordinate;
	}

	Color getColor() {
		return color;
	}

	void setColor(Color color) {
		this.color = color;
	}

}