package hu.hazazs.slg;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

final class PIN {

	private final List<Integer> digits;

	PIN(List<Integer> digits) {
		this.digits = digits;
	}

	List<Integer> getDigits() {
		return digits;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof PIN)) {
			return false;
		}
		PIN other = (PIN) object;
		return Objects.equals(digits, other.digits);
	}

	@Override
	public int hashCode() {
		int sum = digits.stream().mapToInt(Integer::intValue).sum();
		return Objects.hash(sum);
	}

	@Override
	public String toString() {
		return digits.stream().map(digit -> String.valueOf(digit)).collect(Collectors.joining(""));
	}

}