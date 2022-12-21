package hu.hazazs.slg;

import java.util.List;
import java.util.Scanner;

final class SimpleLogicGame {

	private static final int NUMBER_OF_DIGITS = 4;
	private static final int NUMBER_OF_TRIES = 4;
	private static final List<Integer> FORBIDDEN_DIGITS = List.of();

	public static void main(String[] args) {
		new SimpleLogicGame().run();
	}

	private void run() {
		PIN generatedPIN = PINGenerator.getGenerator().generate(NUMBER_OF_DIGITS, FORBIDDEN_DIGITS);
		Cracker cracker = Cracker.getCracker(generatedPIN);
		cracker.createInitialMask();
		try (Scanner scanner = new Scanner(System.in)) {
			UserInput userInput = UserInput.getUserInput(scanner);
			for (int i = 1; i <= NUMBER_OF_TRIES; i++) {
				PIN guessPIN = userInput.readPINFromUser(NUMBER_OF_DIGITS, i);
				cracker.createMask(guessPIN);
				if (guessPIN.equals(generatedPIN)) {
					System.out.print("You have successfully cracked the safe!");
					return;
				}
			}
			System.out.printf("You have failed! The proper PIN was: %s", generatedPIN);
		}
	}

}