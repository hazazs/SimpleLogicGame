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

	PIN readPINFromUser(int numberOfDigits, int index) {
		String input;
		boolean inputInvalid;
		do {
			System.out.print(
					Color.getColor().black(String.format("%s%n%d. try: ", "=".repeat(numberOfDigits * 4), index)));
			input = scanner.nextLine();
			inputInvalid = !input.matches(String.format("\\d{%d}", numberOfDigits));
			if (inputInvalid) {
				System.out.println(Color.getColor().red(String.format("The input must be %d digits.", numberOfDigits)));
			}
		} while (inputInvalid);
		List<Integer> digits = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			digits.add(Integer.parseInt(String.valueOf(input.charAt(i))));
		}
		return new PIN(digits);
	}

}