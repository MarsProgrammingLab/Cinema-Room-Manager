package cinema;

import java.util.Scanner;

public class Cinema {

    public static void printCinema(char cinema[][]) {
        System.out.println("Cinema:");
        for (char[] row : cinema) {
            for (char col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public static void loadCinema(char cinema[][]) {
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0 && j > 0) {
                    // Fill the column headers
                    cinema[i][j] = (char) ('0' + j);
                } else if (j == 0 && i > 0) {
                    // Fill the row headers
                    cinema[i][j] = (char) ('0' + i);
                } else if (i > 0 && j > 0) {
                    // Fill seats with 'S'
                    cinema[i][j] = 'S';
                }
            }
        }
    }

    private static boolean isValidIndex(char[][] array, int row, int col) {
        return row >= 0 && row < array.length && col >= 0 && col < array[row].length;
    }

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        // Declare variables for 2d array
        int ticketCost, rows, cols, seats, rowNumber, colNumber;
        int boughtSeats = 0;
        int currentIncome = 0;
        double percentage = 0;
        boolean isFirstHalf;
        char[][] cinema;

        System.out.println("Enter the number of rows:");
        rows = scr.nextInt();

        System.out.println("Enter the number of seats in each row:");
        cols = scr.nextInt();
        System.out.println();

        seats = cols * rows;
        cinema = new char[rows+1][cols+1];

        loadCinema(cinema);
        printCinema(cinema);
        System.out.println();

        String menuOptions = "1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit";


        while (true) {
            System.out.println(menuOptions);
            int input = scr.nextInt();

            switch (input) {
                case 0:
                    return;
                case 1:
                    System.out.println();
                    printCinema(cinema);
                    System.out.println();
                    break;
                case 2:
                    while (true) {
                        try {
                            System.out.println();
                            System.out.println("Enter a row number:");
                            rowNumber = scr.nextInt();

                            System.out.println("Enter a seat number in that row:");
                            colNumber = scr.nextInt();

                            if (cinema[rowNumber][colNumber] == 'B') {
                                System.out.println();
                                System.out.println("That ticket has already been purchased");
                                System.out.println();
                            } else if (isValidIndex(cinema, rowNumber, colNumber)) {
                                cinema[rowNumber][colNumber] = 'B';
                                boughtSeats++;
                                percentage = (double) boughtSeats * 100 / seats;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println();
                            System.out.println("Wrong input!\n");
                        }
                    }

                    System.out.print("Ticket price: ");

                    isFirstHalf = rowNumber <= (rows / 2);

                    if (seats <= 60 || isFirstHalf) {
                        ticketCost = 10;
                    } else {
                        ticketCost = 8;
                    }
                    currentIncome += ticketCost;
                    System.out.println("$" + ticketCost + "\n");
                    break;
                case 3:
                    int firstHalf, secondHalf;

                    if (seats < 60) {
                        ticketCost = seats * 10;
                    } else {
                        firstHalf = (rows / 2) * cols;
                        secondHalf = seats - firstHalf;
                        ticketCost = firstHalf * 10 + secondHalf * 8;
                    }
                    String formatted = String.format("%.2f", percentage);

                    System.out.println("Number of purchased tickets: " + boughtSeats);
                    System.out.println("Percentage: " + formatted + "%");
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + ticketCost + "\n");
            }
        }
    }
}