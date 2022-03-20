package hu.hazazs.slg;

import java.util.Scanner;

final class SimpleLogicGame {

	private static final int NUMBER_OF_DIGITS = 4;
	private static final int NUMBER_OF_TRIES = 4;

	public static void main(String[] args) {
		new SimpleLogicGame().run();
	}

	private void run() {
		Safe safe = new Safe(new PIN(NUMBER_OF_DIGITS));
		try (Scanner scanner = new Scanner(System.in)) {
			for (int i = 1; i <= NUMBER_OF_TRIES; i++) {
				System.out.printf("%d. try: ", i);
				String PIN = scanner.nextLine();
				if (safe.checkPIN(PIN)) {
					System.out.println("YOU WON!");
					return;
				}
			}
			System.out.printf("YOU LOSE! The proper PIN was: %s", safe.getPIN().getPIN());
		}
	}

}