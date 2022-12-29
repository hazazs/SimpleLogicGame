package hu.hazazs.slg;

import java.util.Scanner;

final class UserInput {

	private static UserInput userInput;
	private final Scanner scanner;

	private UserInput(Scanner scanner) {
		this.scanner = scanner;
	}

	static UserInput getUserInput(Scanner scanner) {
		if (userInput == null) {
			userInput = new UserInput(scanner);
		}
		return userInput;
	}

	String readGuessFromUser(int numberOfDigits, int index, UserInputValidator validator) {
		String guess;
		do {
			System.out.print(
					ANSIColor.getColor().black(String.format("%s%n%d. try: ", "=".repeat(numberOfDigits * 4), index)));
			guess = scanner.nextLine();
		} while (!validator.validate(guess, numberOfDigits));
		return guess;
	}

}