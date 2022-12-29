package hu.hazazs.slg;

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

	boolean validate(String input, int numberOfDigits) {
		boolean valid = true;
		if (!input.matches(String.format("\\d{%d}", numberOfDigits))) {
			System.out
					.println(ANSIColor.getColor().red(String.format("The input must be %d digit(s).", numberOfDigits)));
			valid = false;
		} else {
			if (operatorCheck && !operatorCheck()) {
				System.out.println(ANSIColor.getColor().red("Operator violation(s)."));
				valid = false;
			}
			if (greyRedCheck && !greyRedCheck()) {
				System.out.println(ANSIColor.getColor().red("Invalid (grey, red) character(s)."));
				valid = false;
			}
			if (duplicationCheck && !duplicationCheck()) {
				System.out.println(ANSIColor.getColor().red("Duplication error."));
				valid = false;
			}
		}
		return valid;
	}

	private boolean operatorCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean greyRedCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean duplicationCheck() {
		// TODO Auto-generated method stub
		return false;
	}

}