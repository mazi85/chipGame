package pl.mazi85;

import java.util.Arrays;
import java.util.Scanner;

import static pl.mazi85.ConsoleColors.*;


public class AppGame {

    static int chipToWin = 4;
    static int boardSize = 7;
    static Scanner sc = new Scanner(System.in);
    static char[][] gameArr = new char[boardSize][boardSize];
    static int[] indexes = new int[gameArr.length];


    public static void main(String[] args) {

        //PRZYGOTOWANIE PLANSZY


        for (int i = 0; i < gameArr.length; i++) {
            Arrays.fill(gameArr[i], ' ');
        }
        // GRA
        printBoard(gameArr);

        while (true) {

            fillBoard(gameArr, 'C');
            printBoard(gameArr);
            if (checkBoard(gameArr, 'C')) {
                System.out.println(BLUE + "Wygrał" + RED + " czerwony zawodnik" + RESET);
                break;
            }
            if (checkIfBoardFull(indexes)) {
                System.out.println(BLUE + "REMIS" + RESET);
                break;
            }

            fillBoard(gameArr, 'Ż');
            printBoard(gameArr);
            if (checkBoard(gameArr, 'Ż')) {
                System.out.println(BLUE + "Wygrał" + YELLOW + " żółty zawodnik" + RESET);
                break;
            }
            if (checkIfBoardFull(indexes)) {
                System.out.println(BLUE + "REMIS" + RESET);
                break;
            }
        }
    }

//METODY

    private static boolean checkIfBoardFull(int[] indexes) {
        int count = 0;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] >= boardSize) {
                count++;
            }
        }
        return count >= boardSize;
    }

    private static boolean checkBoard(char[][] gameArr, char chip) {

        for (int i = 0; i < gameArr.length; i++) {
            for (int j = 0; j < gameArr[i].length; j++) {
                if (gameArr[i][j] == chip) {
                    if (checkingHorizontally(gameArr, i, j)) {
                        return true;
                    } else if (checkingVertically(gameArr, i, j)) {
                        return true;
                    } else if (checkingDiagonallyUp(gameArr, i, j)) {
                        return true;
                    } else if (checkingDiagonallyDown(gameArr, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkingDiagonallyDown(char[][] gameArr, int i, int j) {
        int count = 0;

        for (int k = 1; k < chipToWin; k++) {
            if (i < gameArr.length - chipToWin + 1 && j > chipToWin - 2) {
                if (gameArr[i][j] == gameArr[i + k][j - k]) {
                    count++;
                } else
                    break;
            }
        }
        return count == chipToWin - 1;
    }

    private static boolean checkingDiagonallyUp(char[][] gameArr, int i, int j) {
        int count = 0;

        for (int k = 1; k < chipToWin; k++) {
            if (i < gameArr.length - chipToWin + 1 && j < gameArr[i].length - chipToWin + 1) {
                if (gameArr[i][j] == gameArr[i + k][j + k]) {
                    count++;
                } else
                    break;
            }
        }
        return count == chipToWin - 1;

    }

    private static boolean checkingVertically(char[][] gameArr, int i, int j) {

        int count = 0;

        for (int k = 1; k < chipToWin; k++) {
            if (i < gameArr.length - chipToWin + 1) {
                if (gameArr[i][j] == gameArr[i + k][j]) {
                    count++;
                } else
                    break;
            }
        }
        return count == chipToWin - 1;
    }


    private static boolean checkingHorizontally(char[][] gameArr, int i, int j) {
        int count = 0;

        for (int k = 1; k < chipToWin; k++) {
            if (j < gameArr[i].length - chipToWin + 1) {
                if (gameArr[i][j] == gameArr[i][j + k]) {
                    count++;
                } else
                    break;
            }
        }
        return count == chipToWin - 1;


    }

    private static void fillBoard(char[][] gameArr, char chip) {

        String gamer = chip == 'C' ? RED+ "czerwony" : YELLOW +"żółty";
        int column;


        do {
            System.out.print(BLUE + "Umieść żeton " + gamer + BLUE + " w kolumnie [0..6]: " + RESET);
            while (!sc.hasNextInt()) {
                sc.nextLine();
                System.out.print(BLUE + "Podaj wartość liczbową [0..6]: " + RESET);
            }
            column = sc.nextInt();
            sc.nextLine();
        } while (!(column >= 0 && column < gameArr.length));

        if (indexes[column] < indexes.length) {
            gameArr[indexes[column]][column] = chip;
            indexes[column]++;
        } else {
            System.out.println(BLUE + "Kolumna pełna, nie możesz w niej umiejścić żetonu. Strata kolejki" + RESET);
        }

    }

    private static void printBoard(char[][] gameArr) {
        StringBuilder sb = new StringBuilder();

        for (int i = gameArr.length - 1; i >= 0; i--) {
            for (int j = 0; j < gameArr[i].length; j++) {
                sb.append("|");
                if (gameArr[i][j] == 'C') {
                    sb.append(RED + gameArr[i][j] + RESET);
                } else
                    sb.append(YELLOW + gameArr[i][j] + RESET);
            }
            sb.append("|").append("\n");
        }
        System.out.println(sb);
    }

}
