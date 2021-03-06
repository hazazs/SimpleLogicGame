package hu.hazazs.slg;

import java.util.Random;

final class Safe {

	private final String PIN;

	Safe(int digits) {
		PIN = generatePIN(digits);
	}

	String getPIN() {
		return PIN;
	}

	private String generatePIN(int digits) {
		StringBuilder PIN = new StringBuilder();
		for (int i = 0; i < digits; i++) {
			PIN.append(new Random().nextInt(10));
		}
		return PIN.toString();
	}

}