package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static int lengthOfCode;
    private static StringBuilder sb = new StringBuilder();
    private static StringBuilder code = new StringBuilder();
    private static int numberOfCows = 0;
    private static int numberOfBulls = 0;
    private static String guess;
    private static int turn = 1;
    private static Random random = new Random();
    private static int numberOfPossibleSymbols;
    private static int numberOfLetters;
    private static int[] numbers;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        takeSecretCodeLengthInput();
        takeSecretCodePossibleSymbolsInput();
        prepareSecretCode();
        createSecretCode();
        turnsTillGuess();
    }

    private static void takeSecretCodeLengthInput() {
        System.out.println("Input the length of the secret code:");
        lengthOfCode = scanner.nextInt();
        if (lengthOfCode > 10 || lengthOfCode <= 0) {
            System.out.println("Error, the length of the secret code must be between 1 and 10");
            System.exit(0);
        }
    }

    private static void createSecretCode() {
        while(code.length() < lengthOfCode) {
            if (sb.isEmpty()) {
                int digit = random.nextInt(numberOfPossibleSymbols);
                if (digit > 9) {
                    digit = digit - 10 + 'a';
                }
                sb.append(digit);
            }

            int i = 0;
            while (code.length() < lengthOfCode && i < sb.length()) {
                char c = sb.charAt(i);
                int digit = c - '0';
                if (numbers[digit] == 0 && !(code.isEmpty() && digit == 0)) {
                    numbers[digit] = 1;
                    code.append(digit);
                }
                i++;
            }
            sb.setLength(0);
        }
    }

    private static void turnsTillGuess() {
        System.out.println("Okay, let's start the game!");
        while (numberOfBulls != lengthOfCode) {
            System.out.println("Turn: " + turn++);
            guess = scanner.next();
            printResult();
        }
        System.out.println("Congratulations! You guessed the secret code!");
    }

    private static void checkGrade() {
        if (numberOfBulls == lengthOfCode || numberOfBulls >= 1 && numberOfCows <= 0) {
            System.out.println("Grade: " + numberOfBulls + " bulls");
        } else if (numberOfCows >= 1 && numberOfBulls <= 0) {
            System.out.println("Grade: " + numberOfCows + " cow(s).");
        } else if (numberOfBulls >= 1 && numberOfCows >= 1) {
            System.out.println("Grade: " + numberOfCows + " cow(s) and " + numberOfBulls + " bull(s).");
        } else {
            System.out.println("Grade: " + numberOfCows + " cow(s) and " + numberOfBulls + " bull(s).");
        }
    }

    private static void checkForCows() {
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            for (char s : code.toString().toCharArray()) {
                if (c == s) {
                    numberOfCows++;
                    break;
                }
            }
        }
    }

    private static void checkForBulls() {
        String rightCode = code.toString();
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == rightCode.charAt(i)) {
                numberOfBulls++;
                numberOfCows--;
            }
        }
    }

    private static void printResult() {
        numberOfCows = 0;
        numberOfBulls = 0;

        checkForCows();
        checkForBulls();
        checkGrade();
    }

    private static void takeSecretCodePossibleSymbolsInput() {
        System.out.println("Input the number of possible symbols in the code:");
        numberOfPossibleSymbols = scanner.nextInt();

        if (numberOfPossibleSymbols > 36 || numberOfPossibleSymbols <= 0 || numberOfPossibleSymbols < lengthOfCode) {
            System.out.println("Error, the number of possible symbols must be between " + lengthOfCode +  " and 36");
            System.exit(0);
        }
    }

    static void prepareSecretCode() {
        numberOfLetters = numberOfPossibleSymbols - 10;
        char letter = (char) ('a' + numberOfLetters - 1);

        if (numberOfLetters <= 0) {
            System.out.print("The secret code is prepared: ");
            for (int i = 0; i < lengthOfCode; i++) {
                System.out.print("*");
            }
            System.out.printf(" (0-%d).", numberOfPossibleSymbols - 1);
            System.out.println();
        } else {
            System.out.print("The secret code is prepared: ");
            for (int i = 0; i < lengthOfCode; i++) {
                System.out.print("*");
            }
            System.out.printf(" (0-9, a-%c).", letter);
            System.out.println();
        }
        numbers = new int[numberOfPossibleSymbols];
    }
}
