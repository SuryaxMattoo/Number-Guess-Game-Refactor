import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    int diffLvl;
    int finalScore;
    Scanner sc = new Scanner(System.in);
    int max = 100;


    public static boolean validInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } 
        catch (NumberFormatException e) {
            System.out.println("Not an integer, try again.");
            return false;
        }
    }

    Game (int diffLvl, int finalScore){
        this.diffLvl = diffLvl;
        this.finalScore = finalScore;
        System.out.println("Input one of these numbers to select the difficulty of this game");
        System.out.println("1 : Minimum 1 Maximum 10\n2 : Minimum 1 Maximum 50\n3 : Minimum 1 Maximum 100");

        while(true){
            String diffInp = sc.nextLine();
            boolean roar = validInt(diffInp);

            if (roar == false || !diffInp.equals("1") && !diffInp.equals("2") && !diffInp.equals("3")){
                System.out.println("input an integer or a number between 1 and 3");
                continue;
            }
            else{
                this.diffLvl = Integer.parseInt(diffInp);
                break;
            }    
        }
        
        if (this.diffLvl == 1){
            max = 10;
        }
        if (this.diffLvl == 2){
            max = 50;
        }
        if (this.diffLvl == 3){
            max = 100;
        }

        int numTries = 0;
        // Setting the number of tries to zero
        // Using sc.nextInt and scanner for the error checking portion described below

        ArrayList<Integer> names = new ArrayList<Integer>();
        // Making it an empty array so that it does not output the same answer more than
        // once

        int guess = 0;
        final Random rand = new Random();

        final int randNumber = rand.nextInt(1, max + 1);
        System.out.println("I am thinking of a number from " + 1 + " to " + max + ". Can you guess it?"); // Main
                                                                                                            // question

        // Main while loop
        while (guess != randNumber) {
            guess = errorCheck(sc, 1, max);
            ++numTries;
            if (names.contains(guess)) {
                System.out.println("You've already guessed this. Try again");
                // Piece of code that states whether we have guessed the previous number and
                // will tell them to repeat
            }
            if (guess > randNumber) {
                System.out.println("Nope! Go lower.");
                // If we pick the number higher then the selected one then it tells us to go
                // lower
                names.add(guess);
                // Adds one attempt to the guesses

            } else {
                if (guess >= randNumber) {
                    continue;
                }
                System.out.println("Nope! Go higher.");
                // If we select a number too low then we must pick a higher number
            }
            names.add(guess);
            // Adds one attempt to the guesses

        }
        System.out.println("You got it! It took you " + numTries + " tries to guess " + randNumber);
    }

        // Below is how I went about error checking
        public static int errorCheck(final Scanner sc, final int min, final int max) {
            if (!sc.hasNextInt()) {
                System.out.println("This is not a number. Discarding this input");
                // If the input was not an integer then this would occur
                sc.next();
                // Calling function
                return errorCheck(sc, min, max);
                // Runs through the error checking functions defined above
            }
            // Below just says that if the input is a number then all we need to do is
            // continue runnign code and acknowledge that
            // it is a number
            final int input = sc.nextInt();
            System.out.println("This is a number");
            if (input > max || input < min) {
                System.out.println("Hey! this number is out of the range. Try again.");
                return errorCheck(sc, min, max);
                // Error checks to make sure that the number is within the boundaries set
                // earlier
            }
            return input;
        }

}



