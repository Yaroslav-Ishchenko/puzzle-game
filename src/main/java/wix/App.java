package wix;

import wix.game.fifteen.FifteenGame;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        FifteenGame game = new FifteenGame(4, 4);
        int[] arr = game.board();
        for (int i = 1; i <= arr.length; i++) {
            System.out.print(" | " + arr[i - 1] + " | ");
            if (i >= 3 && i % 4 == 0) {
                System.out.println();
                System.out.print("-----------------------------");
                System.out.println();
            }
        }
    }
}
