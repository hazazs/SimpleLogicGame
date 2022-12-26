package hu.hazazs.slg;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

final class Cracker {

	private static Cracker cracker;
	private final PIN generatedPIN;
	private final List<String> operators;
	private final KeyPad keyPad = KeyPad.getKeyPad();

	private Cracker(PIN generatedPIN, Set<Integer> forbiddenDigits) {
		this.generatedPIN = generatedPIN;
		operators = getOperators();
		greyOutForbiddenDigits(forbiddenDigits);
		createMask();
	}

	static Cracker getCracker(PIN generatedPIN, Set<Integer> forbiddenDigits) {
		if (cracker == null) {
			cracker = new Cracker(generatedPIN, forbiddenDigits);
		}
		return cracker;
	}

	KeyPad getKeyPad() {
		return keyPad;
	}

	private List<String> getOperators() {
		List<String> operators = new ArrayList<>();
		for (int i = 0; i < generatedPIN.getDigits().size() - 1; i++) {
			int left = generatedPIN.getDigit(i);
			int right = generatedPIN.getDigit(i + 1);
			operators.add(left > right ? " > " : " < ");
		}
		return operators;
	}

	private void greyOutForbiddenDigits(Set<Integer> forbiddenDigits) {
		for (Integer forbiddenDigit : forbiddenDigits) {
			if (keyPad.getDigits().containsKey(forbiddenDigit)) {
				keyPad.getDigits().get(forbiddenDigit).setColor(Color.GREY);
			}
		}
	}

	private void createMask() {
		StringBuilder mask = new StringBuilder();
		for (int i = 0; i < generatedPIN.getDigits().size(); i++) {
			if (keyPad.getDigits().containsKey(generatedPIN.getDigit(i))
					&& keyPad.getDigits().get(generatedPIN.getDigit(i)).getColor() == Color.GREEN) {
				mask.append(ANSIColor.getColor().green(generatedPIN.getDigit(i)));
			} else {
				mask.append("_");
			}
			if (i < generatedPIN.getDigits().size() - 1) {
				mask.append(operators.get(i));
			}
		}
		System.out.printf("%s%n%n%s", mask, keyPad);
	}

	void checkPIN(PIN guessPIN) {
		updateColors(guessPIN);
		createMask();
	}

	private void updateColors(PIN guessPIN) {
		for (int i = 0; i < guessPIN.getDigits().size(); i++) {
			if (keyPad.getDigits().containsKey(guessPIN.getDigit(i))) {
				Digit digitFromKeyPad = keyPad.getDigits().get(guessPIN.getDigit(i));
				if (guessPIN.getDigit(i) == generatedPIN.getDigit(i)) {
					digitFromKeyPad.setColor(Color.GREEN);
				} else if (generatedPIN.getDigits().contains(guessPIN.getDigit(i))) {
					if (digitFromKeyPad.getColor() != Color.GREEN) {
						digitFromKeyPad.setColor(Color.YELLOW);
					}
				} else {
					if (digitFromKeyPad.getColor() != Color.GREY) {
						digitFromKeyPad.setColor(Color.RED);
					}
				}
			}
		}
	}

	boolean didWeWin() {
		return keyPad.getDigits().values().stream().map(Digit::getColor).filter(color -> color == Color.GREEN)
				.count() == generatedPIN.getDigits().size();
	}

}