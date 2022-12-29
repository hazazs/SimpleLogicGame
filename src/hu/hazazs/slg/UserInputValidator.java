package hu.hazazs.slg;

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
			System.out.println(
					wholeGuess.stream().map(digit -> String.valueOf(digit)).collect(Collectors.joining("   ")));
			if (operatorCheck && !operatorCheck(wholeGuess, cracker.getOperators())) {
				System.out.println(ANSIColor.getColor().red("Operator violation(s)."));
				valid = false;
			}
			if (greyRedCheck && !greyRedCheck(wholeGuess)) {
				System.out.println(ANSIColor.getColor().red("Invalid (grey, red) character(s)."));
				valid = false;
			}
			if (duplicationCheck && !duplicationCheck(wholeGuess)) {
				System.out.println(ANSIColor.getColor().red("Duplication error."));
				valid = false;
			}
		}
		return valid;
	}

	private boolean operatorCheck(List<Integer> wholeGuess, List<String> operators) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean greyRedCheck(List<Integer> wholeGuess) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean duplicationCheck(List<Integer> wholeGuess) {
		// TODO Auto-generated method stub
		return false;
	}

}