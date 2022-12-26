package hu.hazazs.slg;

final class Coordinate {

	private final int row;
	private final int column;

	Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}

	int getRow() {
		return row;
	}

	int getColumn() {
		return column;
	}

}