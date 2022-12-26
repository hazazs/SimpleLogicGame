package hu.hazazs.slg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

final class PINGenerator {

	private static PINGenerator generator;

	private PINGenerator() {
	}

	static PINGenerator getGenerator() {
		if (generator == null) {
			generator = new PINGenerator();
		}
		return generator;
	}

	PIN generate(int numberOfDigits, Set<Integer> forbiddenDigits) {
		List<Integer> digits = new ArrayList<>(numberOfDigits);
		Random random = new Random();
		while (digits.size() < numberOfDigits) {
			int digit = random.nextInt(10);
			if (!digits.contains(digit) && !forbiddenDigits.contains(digit)) {
				digits.add(digit);
			}
		}
		return new PIN(digits);
	}

}