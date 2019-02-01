package orderofoperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OrderOfOperationsQuiz {
	Map<String, String[]> shuffledQuiz;
	OrderOfOperations quiz;

	{
		quiz = new OrderOfOperations();
	}

	public static void main(String[] args) {
		OrderOfOperationsQuiz quiz = new OrderOfOperationsQuiz();
		quiz.go();
	}

	private void go() {
		Scanner sc = new Scanner(System.in);
		String[] guesses;

		while (true) {
			shuffledQuiz = quiz.getShuffledAnswers();
			printShuffledQuiz();

			guesses = getPlayerGuesses(sc);
			if (guesses != null && guesses.length == shuffledQuiz.keySet().size()) {
				System.out.println(quiz.judgeAnswers(guesses));
				System.out.print("See unshuffled (a)nswers or (t)ry again? ");
				if(sc.nextLine().equalsIgnoreCase("a")) {
					shuffledQuiz = quiz.getOriginalMap();
					printShuffledQuiz();
				}else {
					continue;
				}
				System.out.print("\n(Q)uit or any other key to try again: ");
				if(!sc.nextLine().equalsIgnoreCase("q")) {
					continue;
				}
			}
			break;
		}

		sc.close();

	}

	private String[] getPlayerGuesses(Scanner sc) {
		String[] answers = null;

	OUTER:	while (true) {
			System.out.print(
					"\nEnter the correct order of operators' precedence in the following format:\n\t(F,D,A,E,C,B)\n>> ");
			answers = sc.nextLine().split(",");

			if (answers.length < shuffledQuiz.keySet().size()) {
				System.out.print("You did not enter enough answers.  Try again? (y/n) ");
				if (sc.nextLine().equalsIgnoreCase("Y")) {
					continue;
				} else
					break;
			}

			for (int i = 0; i < shuffledQuiz.keySet().size(); i++) {
				answers[i] = answers[i].toUpperCase().trim();
				if (!shuffledQuiz.containsKey(answers[i])) {
					System.out.println(answers[i] + " is an invalid response.  Try again? (y/n) ");
					if (sc.nextLine().equalsIgnoreCase("Y")) {
						continue OUTER;
					} else
						break OUTER;
				}
			}
			break;
		}
		return answers;
	}

	void printShuffledQuiz() {
		List<String> keys = new ArrayList<>(shuffledQuiz.keySet());

		System.out.printf("\n\n%28s\t\t%15s%n", "Operator", "Examples");
		separator();
		for (String key : keys) {
			System.out.printf("%31s (%s) \t%s%n", shuffledQuiz.get(key)[0], key, shuffledQuiz.get(key)[1]);
		}
	}

	void separator() {
		System.out.println("==================================================================");
	}
}
