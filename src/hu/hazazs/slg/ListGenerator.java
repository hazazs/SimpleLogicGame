package hu.hazazs.slg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

final class ListGenerator {

	private static ListGenerator generator;

	private ListGenerator() {
	}

	static ListGenerator getGenerator() {
		if (generator == null) {
			generator = new ListGenerator();
		}
		return generator;
	}

	List<Integer> generate(int numberOfDigits, Set<Integer> forbiddenDigits) {
		List<Integer> digits = new ArrayList<>(numberOfDigits);
		Random random = new Random();
		while (digits.size() < numberOfDigits) {
			int digit = random.nextInt(10);
			if (!digits.contains(digit) && !forbiddenDigits.contains(digit)) {
				digits.add(digit);
			}
		}
		return digits;
	}

}