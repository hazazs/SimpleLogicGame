package hu.hazazs.slg;

import java.util.HashSet;
import java.util.Set;

final class Cracker {

	private static Cracker cracker;
	private final PIN generatedPIN;
	private final Set<Integer> grey;
	private final Set<Integer> green = new HashSet<>();
	private final Set<Integer> yellow = new HashSet<>();
	private final Set<Integer> red = new HashSet<>();

	private Cracker(PIN generatedPIN, Set<Integer> forbiddenDigits) {
		this.generatedPIN = generatedPIN;
		grey = forbiddenDigits;
	}

	static Cracker getCracker(PIN generatedPIN, Set<Integer> forbiddenDigits) {
		if (cracker == null) {
			cracker = new Cracker(generatedPIN, forbiddenDigits);
		}
		return cracker;
	}

	Set<Integer> getGrey() {
		return grey;
	}

	Set<Integer> getGreen() {
		return green;
	}

	Set<Integer> getYellow() {
		return yellow;
	}

	Set<Integer> getRed() {
		return red;
	}

	void createInitialMask() {
		createMask(true);
	}

	void createMask(PIN guessPIN) {
		createMask(false, guessPIN);
	}

	private void createMask(boolean initial, PIN... PINs) {
		if (!initial) {
			updateSets(PINs[0]);
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < generatedPIN.getDigits().size(); i++) {
			if (green.contains(generatedPIN.getDigit(i))) {
				builder.append(Color.getColor().green(generatedPIN.getDigit(i)));
			} else {
				builder.append("_");
			}
			if (i < generatedPIN.getDigits().size() - 1) {
				builder.append(getRelationalOperator(i));
			}
		}
		System.out.println(builder);
	}

	private void updateSets(PIN guessPIN) {
		for (int i = 0; i < guessPIN.getDigits().size(); i++) {
			if (guessPIN.getDigit(i) == generatedPIN.getDigit(i)) {
				green.add(guessPIN.getDigit(i));
				yellow.remove(guessPIN.getDigit(i));
			} else if (generatedPIN.getDigits().contains(guessPIN.getDigit(i))) {
				if (!green.contains(guessPIN.getDigit(i))) {
					yellow.add(guessPIN.getDigit(i));
				}
			} else {
				if (!grey.contains(guessPIN.getDigit(i))) {
					red.add(guessPIN.getDigit(i));
				}
			}
		}
	}

	private String getRelationalOperator(int index) {
		int left = generatedPIN.getDigits().get(index);
		int right = generatedPIN.getDigits().get(index + 1);
		if (left > right) {
			return " > ";
		} else {
			return " < ";
		}
	}

}