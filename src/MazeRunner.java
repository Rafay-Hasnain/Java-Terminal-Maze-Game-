import java.util.Scanner; // The Scanner class which is inside the java.util library is for taking input from the users for example reading numbers or strings etc

// Static (Variable or Method) = Belongs to the class itself e.g main it does not belong to any particular object
// Non Static (Variable or Method) = Belongs to an object instance of the class when we create it using new

public class MazeRunner {

    String MazeArray[][]; // MazeArray is a global class level variable. We can only declare global class
                          // variables outside the main method

    int PlayerRow = 1; // We only need public if other classes need to access these variables no need
                       // if they are in the same class. Same goes for methods
    int PlayerColumn = 1;

    int stepsTaken = 0;

    long startTime; // System.currentTimeMillis() gives a large number (with long data type) in
                    // milliseconds which doesn't fit in an int
    long endTime; // We can also use int but then we would have to convert start time and end time
                  // variables below into int as well

    int bestTimeScore = 0;

    Scanner myObj = new Scanner(System.in); // Scanner is a class so we need to make an object first to use it. It acts
                                            // like a reader that listens to what the user types into the keyboard

    public void inititalizeMaze() { // Void as it does not return anything. Public is used so that the method can be
                                    // called from outside the class

        MazeArray = new String[][] { // This will assign a new array object to the existing global MazeArray variable
                                     // so the class variable points to this new array updating the global array
                { "#", "#", "#", "#", "#", "#", "#" },
                { "#", "P", ".", ".", ".", ".", "#" },
                { "#", "#", "#", "#", ".", "#", "#" },
                { "#", ".", ".", ".", ".", ".", "#" },
                { "#", "#", "#", ".", "#", "#", "#" },
                { "#", ".", ".", ".", ".", "E", "#" },
                { "#", "#", "#", "#", "#", "#", "#" }
        };
    }

    public void printMaze() { // Void because it doesn't return anything

        for (int i = 0; i <= MazeArray.length - 1; i++) { // It MazeArray.length provides the number of rows
            for (int j = 0; j <= MazeArray[i].length - 1; j++) { // This MazeArray[i].length will fetch the number of
                                                                 // columns for that particular row
                System.out.print(MazeArray[i][j]);
            }
            System.out.println();
        }
    }

    public String isValidMove(String DirectionMovedLetter) {

        // First we will have to update PlayerRow and PlayerColumn creating temporary
        // variables and then check the new position otherwise it will check the current
        // position of P
        int newRow = PlayerRow;
        int newColumn = PlayerColumn;

        if (DirectionMovedLetter.equals("W")) {
            newRow = newRow - 1; // Up
        } else if (DirectionMovedLetter.equals("A")) {
            newColumn = newColumn - 1; // Left
        } else if (DirectionMovedLetter.equals("S")) {
            newRow = newRow + 1; // Down
        } else if (DirectionMovedLetter.equals("D")) {
            newColumn = newColumn + 1; // Right
        }

        if (MazeArray[newRow][newColumn].equals("#")) {
            return "Hit a wall";
        }

        if (MazeArray[newRow][newColumn].equals("E")) {
            return "Exit reached you won";
        }

        return "Moved";
    }

    public void movePlayer(String MoveDirection) {

        MazeArray[PlayerRow][PlayerColumn] = "."; // Clear the old position

        if (MoveDirection.equals("W")) {
            PlayerRow = PlayerRow - 1; // Up
            stepsTaken += 1;
        } else if (MoveDirection.equals("A")) {
            PlayerColumn = PlayerColumn - 1; // Left
            stepsTaken += 1;
        } else if (MoveDirection.equals("S")) {
            PlayerRow = PlayerRow + 1; // Down
            stepsTaken += 1;
        } else if (MoveDirection.equals("D")) {
            PlayerColumn = PlayerColumn + 1; // Right
            stepsTaken += 1;
        }

        if (!MazeArray[PlayerRow][PlayerColumn].equals("E")) { // P will only be placed if it is not on E because if P
                                                               // is placed on E player won method will never be able to
                                                               // check win since E has gone
            MazeArray[PlayerRow][PlayerColumn] = "P"; // Update player position
        }
    }

    public boolean hasPlayerWon() {

        if (MazeArray[PlayerRow][PlayerColumn].equals("E")) {
            return true;
        }

        return false;
    }

    public void displayResults() {

        endTime = (System.currentTimeMillis() - startTime) / 1000; // This will convert it into seconds

        System.out.println("The number of steps taken:" + stepsTaken);
        System.out.println("Time played:" + endTime + " seconds");
    }

    public void updateScore() { // Local variables only exist when the method is called and are lost when the
                                // method is done

        if (bestTimeScore == 0 || endTime < bestTimeScore) { // Update if the new time is less than the best time
            bestTimeScore = (int) endTime; // (int) converts long into an integer so we need to match the variables
        }
    }

    public void showHighScore() {
        System.out.println("The highest score is:" + bestTimeScore + " seconds");
    }

    public void showInstructions() {
        System.out.println("Instructions");
        System.out.println("Reach the exit ('E') in the least amount of time for maximum score");
        System.out.println("Press W to Move Up");
        System.out.println("Press A to Move Left");
        System.out.println("Press S to Move Down");
        System.out.println("Press D to Move Right");
    }

    public void showCredits() {
        System.out.println("Created by Rafay Hasnain");
    }

    public void playGame() {

        startTime = System.currentTimeMillis(); // System.currentTimeMillis() returns the current time in milliseconds
                                                // stored in startTime

        PlayerRow = 1; // Reset these values otherwise P will be at the old position
        PlayerColumn = 1;
        stepsTaken = 0;

        inititalizeMaze(); // We need to initialize the maze first as it needs to exist before we can make
                           // a move
        printMaze();

        boolean running = true;

        while (running) {

            System.out.println("Press Q to quit the game or W/A/S/D to move the player:");
            String Choice = myObj.nextLine().trim().toUpperCase();

            if (Choice.equals("Q")) {

                System.out.println("Exiting the Game");
                displayResults();
                running = false;
            }

            else if (Choice.equals("W") || Choice.equals("A") || Choice.equals("S") || Choice.equals("D")) {

                if (isValidMove(Choice).equals("Hit a wall")) {
                    System.out.println("Hit a Wall");
                    printMaze();
                } else {
                    movePlayer(Choice);
                    printMaze();

                    System.out.println("Moved");

                    if (hasPlayerWon()) {
                        System.out.println("You Won");
                        displayResults();
                        updateScore();
                        running = false;
                    }
                }
            }

            else {
                System.out.println("Invalid Entry");
                printMaze();
            }
        }
    }
}