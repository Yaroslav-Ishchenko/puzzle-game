package wix;

import wix.game.IPuzzleGame;
import wix.game.fifteen.FifteenGame;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import static wix.game.IPuzzleGame.Move.DOWN;
import static wix.game.IPuzzleGame.Move.LEFT;
import static wix.game.IPuzzleGame.Move.RIGHT;
import static wix.game.IPuzzleGame.Move.UP;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        FifteenGame game = new FifteenGame(3, 3);
        Integer[] arr = game.getBoard();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            String input = keyboard.nextLine();
            Runtime.getRuntime().exec("clear");
            if(input != null) {
                System.out.println("Your input is : " + input);
                if ("quit".equals(input)) {
                    System.out.println("Exit programm");
                    exit = true;
                } else if ("l".equals(input)) {
                    game.moveTo(LEFT);
                }  else if ("r".equals(input)) {
                    game.moveTo(RIGHT);
                } else if ("u".equals(input)) {
                    game.moveTo(UP);
                } else if ("d".equals(input)) {
                    game.moveTo(DOWN);
                }
                for (int i = 1; i <= arr.length; i++) {
                    System.out.print(" | " + arr[i - 1] + " | ");
                    if (i >= 2 && i % 3 == 0) {
                        System.out.println();
                        System.out.print("-----------------------------");
                        System.out.println();
                    }
                }
            }
        }
        keyboard.close();

    }
}
