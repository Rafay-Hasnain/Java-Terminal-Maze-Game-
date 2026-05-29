import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MazeRunner game = new MazeRunner();
        Scanner myObj = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("Press 1 to Play Game or 2 Instructions / 3 Credits / 4 High Score / 5 Exit:");
            int choice = myObj.nextInt();
            myObj.nextLine();

            if (choice == 1) {
                game.playGame();
            } else if (choice == 2) {
                game.showInstructions();
            } else if (choice == 3) {
                game.showCredits();
            } else if (choice == 4) {
                game.showHighScore();
            } else if (choice == 5) {
                System.out.println("Exiting");
                running = false;
            } else {
                System.out.println("You entered the wrong value!");
            }
        }
    }
}