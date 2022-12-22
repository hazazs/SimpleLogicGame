package hu.hazazs.slg;

import java.util.List;
import java.util.stream.Collectors;

final class PIN {

	private final List<Integer> digits;

	PIN(List<Integer> digits) {
		this.digits = digits;
	}

	List<Integer> getDigits() {
		return digits;
	}

	Integer getDigit(int index) {
		return digits.get(index);
	}

	@Override
	public String toString() {
		return digits.stream().map(digit -> String.valueOf(digit)).collect(Collectors.joining(""));
	}

}