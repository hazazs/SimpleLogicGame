package hu.hazazs.slg;

import java.util.ArrayList;
import java.util.List;
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

	PIN readPINFromUser(int numberOfDigits, int index, UserInputValidator validator) {
		String input;
		do {
			System.out.print(
					ANSIColor.getColor().black(String.format("%s%n%d. try: ", "=".repeat(numberOfDigits * 4), index)));
			input = scanner.nextLine();
		} while (!validator.validate(input, numberOfDigits));
		List<Integer> digits = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			digits.add(Integer.parseInt(String.valueOf(input.charAt(i))));
		}
		return new PIN(digits);
	}

}