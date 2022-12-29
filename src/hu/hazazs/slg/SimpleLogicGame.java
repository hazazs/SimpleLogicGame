package hu.hazazs.slg;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

final class SimpleLogicGame {

	private static final int NUMBER_OF_DIGITS = 4;
	private static final int NUMBER_OF_TRIES = 4;
	private static final Difficulty difficulty = Difficulty.HARD;
	private static final Set<Integer> FORBIDDEN_DIGITS = new HashSet<>();
	private static final boolean OPERATOR_CHECK = false;
	private static final boolean GREY_RED_CHECK = false;
	private static final boolean DUPLICATION_CHECK = false;

	public static void main(String[] args) {
		new SimpleLogicGame().run();
	}

	private void run() {
		generateForbiddenDigits();
		if (10 - FORBIDDEN_DIGITS.size() < NUMBER_OF_DIGITS) {
			System.out.print(ANSIColor.getColor()
					.red("Invalid configuration:\n10 - FORBIDDEN_DIGITS must be at least NUMBER_OF_DIGITS."));
			return;
		}
		PIN generatedPIN = new PIN(ListGenerator.getGenerator().generate(NUMBER_OF_DIGITS, FORBIDDEN_DIGITS));
		Cracker cracker = Cracker.getCracker(generatedPIN, FORBIDDEN_DIGITS);
		try (Scanner scanner = new Scanner(System.in)) {
			UserInput userInput = UserInput.getUserInput(scanner);
			UserInputValidator validator = UserInputValidator.getValidator(cracker, OPERATOR_CHECK, GREY_RED_CHECK,
					DUPLICATION_CHECK);
			for (int i = 1; i <= NUMBER_OF_TRIES; i++) {
				String guess = userInput.readGuessFromUser(cracker.getNumberOfNotGreenDigits(), i, validator);
				cracker.checkPIN(new PIN(cracker.createWholeGuess(guess)));
				if (cracker.didWeWin()) {
					System.out.print(ANSIColor.getColor()
							.green("Congratulations!\nYou have successfully cracked the PIN code!"));
					return;
				}
			}
			System.out.print(ANSIColor.getColor()
					.red(String.format("You have failed!%nThe proper PIN code was: %s", generatedPIN)));
		}
	}

	private void generateForbiddenDigits() {
		int numberOfForbiddenDigits = switch (difficulty) {
			case EASY -> 4;
			case MEDIUM -> 2;
			case HARD -> 0;
		};
		FORBIDDEN_DIGITS.addAll(ListGenerator.getGenerator().generate(numberOfForbiddenDigits, Set.of()));
	}

}