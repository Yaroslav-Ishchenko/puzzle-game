package game.puzzle.presentation;

import game.puzzle.IPuzzleGame;

import java.util.Scanner;

import static game.puzzle.core.Move.DOWN;
import static game.puzzle.core.Move.LEFT;
import static game.puzzle.core.Move.RIGHT;
import static game.puzzle.core.Move.UP;
import static java.lang.Math.sqrt;

public class GamePresenter implements IGamePresenter {
    //Commands
    private static final String QUIT = "quit";
    private static final String RESET = "reset";
    private static final String L = "l";
    private static final String R = "r";
    private static final String D = "d";
    private static final String U = "u";

    private final Scanner keyboard;
    private final IPuzzleGame puzzleGame;

    public GamePresenter(IPuzzleGame puzzleGame) {
        this.keyboard = new Scanner(System.in);
        this.puzzleGame = puzzleGame;
        printInstructions();
    }

    private void printInstructions() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Hello");
        System.out.println("Your goal is to arrange all items on the grid in ascending order...");
        System.out.println("Where 'X' should be at top lef corner, while other elements would be follow it in ascending order like 1,2,3,4...");
        System.out.println("To move 'X' to the LEFT enter symbol 'l' and press ENTER");
        System.out.println("To move 'X' to the RIGHT enter symbol 'r' and press ENTER");
        System.out.println("To move 'X' to the UP enter symbol 'u' and press ENTER");
        System.out.println("To move 'X' to the DOWN enter symbol 'd' and press ENTER");
        System.out.println("To exit game enter 'quit' and press ENTER");
        System.out.println("To start game from beginning enter 'reset' and press ENTER");
        System.out.println();
    }

    @Override
    public void begin() {
        boolean exit = false;
        draw();
        while (!exit) {
            String input = keyboard.nextLine();
            if (input != null && !input.isEmpty()) {
                System.out.println("Your input is : " + input);
                if (QUIT.equals(input)) {
                    System.out.println("Exit programm");
                    exit = true;
                }

                if (RESET.equals(input)) {
                    puzzleGame.reset();
                } else if (L.equals(input)) {
                    puzzleGame.moveTo(LEFT);
                } else if (R.equals(input)) {
                    puzzleGame.moveTo(RIGHT);
                } else if (U.equals(input)) {
                    puzzleGame.moveTo(UP);
                } else if (D.equals(input)) {
                    puzzleGame.moveTo(DOWN);
                }
                draw();
                if (puzzleGame.isCompleted()) {
                    System.out.println("Congrats, you are winner!!! Exiting now.");
                    exit = true;
                }

            } else {
                System.out.println("Please enter one of valid values {"
                    + L + ", " + R + ", " + U + ", " + D + ", " + RESET + ", " + QUIT + "}");
                draw();
            }
        }
        keyboard.close();
    }

    private void draw() {
        Integer[] grid = puzzleGame.getGrid();
        int widthHeight = (int) sqrt(grid.length);
        for (int i = 1; i <= grid.length; i++) {
            System.out.print(" | " + toSymbol(grid[i - 1]) + " | ");
            if (i >= widthHeight - 1 && i % widthHeight == 0) {
                System.out.println();
                System.out.print("-----------------------------");
                System.out.println();
            }
        }
    }

    private String toSymbol(Integer i) {
        return i == 0 ? "X" : String.valueOf(i);
    }

}
