// Task 1 - Guess the Number
/*
1. Generate a random number within a specified range, such as 1 to 100.
2. Prompt the user to enter their guess for the generated number.
3. Compare the user's guess with the generated number and provide feedback on whether the guess
is correct, too high, or too low.
4. Repeat steps 2 and 3 until the user guesses the correct number.
You can incorporate additional details as follows:
1. Limit the number of attempts the user has to guess the number.
2. Add the option for multiple rounds, allowing the user to play again.
3. Display the user's score, which can be based on the number of attempts taken or rounds won.
 */

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int score = 0;
        int attempts = 0;
        while (true) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            

            System.out.println("Welcome to Guess the Number!");
            System.out.println("I've picked a number between " + minRange + " and " + maxRange + ". Try to guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + ". Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You've guessed the number.");
                    score++;
                    break;
                } else if (userGuess < randomNumber)
                    System.out.println("Too low. Try again.");
                else
                    System.out.println("Too high. Try again.");

                attempts++;
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've run out of attempts. The number was " + randomNumber + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }
        System.out.println("Your final score is: " + attempts);
    }
}