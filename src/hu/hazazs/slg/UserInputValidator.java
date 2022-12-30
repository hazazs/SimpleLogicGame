package hu.hazazs.slg;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class UserInputValidator {

	private static UserInputValidator validator;
	private final Cracker cracker;
	private final boolean operatorCheck;
	private final boolean greyRedCheck;
	private final boolean duplicationCheck;

	private UserInputValidator(Cracker cracker, boolean operatorCheck, boolean greyRedCheck, boolean duplicationCheck) {
		this.cracker = cracker;
		this.operatorCheck = operatorCheck;
		this.greyRedCheck = greyRedCheck;
		this.duplicationCheck = duplicationCheck;
	}

	static UserInputValidator getValidator(Cracker cracker, boolean operatorCheck, boolean greyRedCheck,
			boolean duplicationCheck) {
		if (validator == null) {
			validator = new UserInputValidator(cracker, operatorCheck, greyRedCheck, duplicationCheck);
		}
		return validator;
	}

	boolean validate(String guess, int numberOfDigits) {
		boolean valid = true;
		if (!guess.matches(String.format("\\d{%d}", numberOfDigits))) {
			System.out
					.println(ANSIColor.getColor().red(String.format("The input must be %d digit(s).", numberOfDigits)));
			valid = false;
		} else {
			List<Integer> wholeGuess = cracker.createWholeGuess(guess);
			if (operatorCheck) {
				List<Character> output = operatorCheck(wholeGuess);
				if (output.stream().anyMatch(character -> character.getColor() == Color.RED)) {
					System.out.println(ANSIColor.getColor().red("Operator violation(s): ")
							+ output.stream().map(Character::toString).collect(Collectors.joining("")));
					valid = false;
				}
			}
			if (greyRedCheck) {
				List<Character> output = greyRedCheck(wholeGuess);
				if (output.stream().anyMatch(character -> character.getColor() != Color.BLACK)) {
					System.out.println(ANSIColor.getColor().red("Invalid character(s):  ")
							+ output.stream().map(Character::toString).collect(Collectors.joining(" ")));
					valid = false;
				}
			}
			if (duplicationCheck) {
				List<Character> output = duplicationCheck(wholeGuess);
				if (output.stream().anyMatch(character -> character.getColor() == Color.RED)) {
					System.out.println(ANSIColor.getColor().red("Duplication error:     ")
							+ output.stream().map(Character::toString).collect(Collectors.joining(" ")));
					valid = false;
				}
			}
			if (valid) {
				System.out.println(
						wholeGuess.stream().map(digit -> String.valueOf(digit)).collect(Collectors.joining("   ")));
			}
		}
		return valid;
	}

	private List<Character> operatorCheck(List<Integer> wholeGuess) {
		List<Character> output = new ArrayList<>(2 * wholeGuess.size() - 1);
		for (int i = 0; i < wholeGuess.size(); i++) {
			output.add(new Character(wholeGuess.get(i)));
			if (i < wholeGuess.size() - 1) {
				String operator = cracker.getOperators().get(i).trim();
				if (("<".equals(operator) && wholeGuess.get(i) >= wholeGuess.get(i + 1))
						|| (">".equals(operator) && wholeGuess.get(i) <= wholeGuess.get(i + 1))) {
					output.add(new Character(operator, Color.RED));
				} else {
					output.add(new Character(operator));
				}
			}
		}
		return output;
	}

	private List<Character> greyRedCheck(List<Integer> wholeGuess) {
		List<Character> output = new ArrayList<>(wholeGuess.size());
		for (int i = 0; i < wholeGuess.size(); i++) {
			if (cracker.getKeyPad().getDigits().containsKey(wholeGuess.get(i))) {
				Digit digitFromKeyPad = cracker.getKeyPad().getDigits().get(wholeGuess.get(i));
				if (digitFromKeyPad.getColor() == Color.GREY) {
					output.add(new Character(digitFromKeyPad.getValue(), Color.GREY));
				} else if (digitFromKeyPad.getColor() == Color.RED) {
					output.add(new Character(digitFromKeyPad.getValue(), Color.RED));
				} else {
					output.add(new Character(digitFromKeyPad.getValue()));
				}
			}
		}
		return output;
	}

	private List<Character> duplicationCheck(List<Integer> wholeGuess) {
		List<Character> output = new ArrayList<>(wholeGuess.size());
		for (int i = 0; i < wholeGuess.size(); i++) {
			Integer currentDigit = wholeGuess.get(i);
			if (wholeGuess.stream().filter(digit -> digit == currentDigit).count() > 1) {
				output.add(new Character(currentDigit, Color.RED));
			} else {
				output.add(new Character(currentDigit));
			}
		}
		return output;
	}

}