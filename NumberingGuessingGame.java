import java.util.Random;
import java.util.Scanner;

public class NumberingGuessingGame {
    
    public static int playGame(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1; // 1 to 100
        int attemptsAllowed = 7;
        int attemptsTaken = 0;
        int score = 0;
        
        System.out.println("\n🎯 Guess the number between 1 and 100!");
        
        while (attemptsTaken < attemptsAllowed) {
            System.out.print("Attempt " + (attemptsTaken + 1) + "/" + attemptsAllowed + " - Enter your guess: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.next(); 
                continue;
            }
            
            int guess = scanner.nextInt();
            attemptsTaken++;
            
            if (guess == numberToGuess) {
                System.out.println("✅ Correct! You guessed it in " + attemptsTaken + " attempts.");
                score = (attemptsAllowed - attemptsTaken + 1) * 10;
                break;
            } else if (guess < numberToGuess) {
                System.out.println("🔻 Too low! Try again.");
            } else {
                System.out.println("🔺 Too high! Try again.");
            }
        }
        
        if (attemptsTaken == attemptsAllowed && score == 0) {
            System.out.println("😢 Out of attempts! The number was " + numberToGuess + ".");
        }
        
        return score;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;
        
        while (true) {
            int roundScore = playGame(scanner);
            totalScore += roundScore;
            System.out.println("🎯 Round Score: " + roundScore + " | 🏆 Total Score: " + totalScore);
            
            System.out.print("\nDo you want to play again? (y/n): ");
            String choice = scanner.next().toLowerCase();
            if (!choice.equals("y")) {
                System.out.println("\nThanks for playing! Your final score is " + totalScore + ".");
                break;
            }
        }
        
        scanner.close();
    }
}
