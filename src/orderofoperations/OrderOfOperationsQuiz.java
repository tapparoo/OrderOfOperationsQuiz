package orderofoperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class OrderOfOperationsQuiz {
	Map<String, String[]> shuffledQuiz;
	OrderOfOperations quiz;
	List<String> keys;
	Set<String> guesses;

	{
		quiz = new OrderOfOperations();
	}

	public static void main(String[] args) {
		OrderOfOperationsQuiz quiz = new OrderOfOperationsQuiz();
		quiz.go();
	}

	private void go() {
		boolean shuffle = false;
		Scanner sc = new Scanner(System.in);

		while (true) {
			if (shuffle) {
				quiz.shuffleList();
				shuffle = false;
			}
			shuffledQuiz = quiz.getShuffledAnswers();
			keys = new ArrayList<>(shuffledQuiz.keySet());
			guesses = new HashSet<>();

			printShuffledQuiz();

			getPlayerGuesses(sc);

			if (guesses.size() == keys.size()) {
				System.out.println(quiz.judgeAnswers(guesses));
				System.out.print("See unshuffled (a)nswers or (t)ry again? ");
				if (sc.next().equalsIgnoreCase("a")) {
					shuffledQuiz = quiz.getOriginalMap();
					guesses = new HashSet<>();
					System.out.println("\n\n**********************SOLUTION BELOW************************");
					printShuffledQuiz();
				} else {
					shuffle = true;
					continue;
				}

				System.out.print("\n(Q)uit or any other key to try again: ");
				if (!sc.next().equalsIgnoreCase("q")) {
					continue;
				} else {
					shuffle = true;
				}
			}
			break;
		}
		sc.close();
	}

	private void getPlayerGuesses(Scanner sc) {
		System.out.print(
				"\nEnter the correct order of operators' precedence one key at a time, followed by Enter: \n>> ");

		OUTER: for (int i = 0; i < shuffledQuiz.size(); i++) {
			String answer = "";
			while (true) {
				answer = sc.next().toUpperCase();
				if (!shuffledQuiz.containsKey(answer)) {
					System.out.println(answer + " is an invalid response.  Try again? (y/n) ");
					if (sc.next().equalsIgnoreCase("Y")) {
						System.out.print(">> ");
						continue;
					} else
						break OUTER;
				} else if (guesses.contains(answer)) {
					System.out.println(answer + " was already guessed.  Try again? (y/n) ");
					if (sc.next().equalsIgnoreCase("Y")) {
						System.out.print(">> ");
						continue;
					} else
						break OUTER;
				} else {
					guesses.add(answer);
					printShuffledQuiz();
					System.out.print(">> ");
				}
				break;
			}
		}
	}

	void printShuffledQuiz() {
		String underlinedSpaces = " \u0332 \u0332";
		List<String> guessed = new ArrayList<>(guesses);

		System.out.printf("\n\n%24s   %s    %s \t\t    %s%n", "Operator", "Key", "Examples", "Order");
		separator();

		for (int i = 0; i < shuffledQuiz.size(); i++) {
			String key = keys.get(i);
			String strike = guessed.contains(key) ? strikeOutText(key) : key;

			if ((guesses.size() - 1 < i)) {
				System.out.printf("%25s  %2s\t  %-28s%s\n", shuffledQuiz.get(key)[0], strike, shuffledQuiz.get(key)[1],
						underlinedSpaces);
			} else {
				System.out.printf("%25s  %2s\t  %-28s%s\n", shuffledQuiz.get(key)[0], strike, shuffledQuiz.get(key)[1],
						guessed.get(i));
			}
		}
	}

	// Tried to apply this to the whole line, but it really screws up the
	// font/spacing
	String strikeOutText(String s) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			sb.append("-");
			sb.append(s.substring(i, i + 1));
			sb.append("\u0336-");
		}

		return sb.toString();
	}

	void separator() {
		System.out.println("==================================================================");
	}
}
