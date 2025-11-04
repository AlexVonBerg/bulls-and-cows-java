package cinema;

import java.util.Scanner;

public class Cinema {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    static void start() {
        getCinemaDetails();
        String[][] seats = createCinema();
        boolean[][] sitIsTaken = sitIsTaken();
        menu(seats, sitIsTaken);
    }

    static int numberOfRows;
    static int numberOfSeatsInRow;
    static int numberOfTotalSeats;
    static int seat;
    static int row;

    static void getCinemaDetails() {
        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numberOfSeatsInRow = scanner.nextInt();
        scanner.nextLine();
        numberOfTotalSeats = numberOfRows * numberOfSeatsInRow;
        System.out.println();
    }

    static int currentIncome = 0;

    static void getTicketPrice(int numberOfRows) {
        if (numberOfTotalSeats <= 60) {
            System.out.println("Ticket price: $10");
            currentIncome += 10;
        } else {
            int frontRows = numberOfRows / 2;
            if (row <= frontRows) {
                System.out.println("Ticket price: $10");
                currentIncome += 10;
            } else {
                System.out.println("Ticket price: $8");
                currentIncome += 8;
            }
        }
        System.out.println();
    }

    static String[][] createCinema() {
        String[][] seats = new String[numberOfRows][numberOfSeatsInRow];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                seats[i][j] = "S";
            }
        }
        return seats;
    }

    static boolean[][] sitIsTaken() {
        boolean[][] sitIsTaken = new boolean[numberOfRows][numberOfSeatsInRow];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                sitIsTaken[i][j] = false;
            }
        }
        return sitIsTaken;
    }

    static void printSeats(String[][] seats) {
        System.out.println("Cinema:");

        System.out.print("  ");
        for (int k = 0; k < numberOfSeatsInRow; k++) {
            System.out.print((k + 1) + " ");
        }
        System.out.println();

        for (int i = 0; i < numberOfRows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int numberOfPurchasedTickets = 0;

    static void markSeat(String[][] seats, boolean[][] sitIsTaken, int row, int seat) {
        seats[row - 1][seat - 1] = "B";
        sitIsTaken[row - 1][seat - 1] = true;
        numberOfPurchasedTickets++;

    }

    static void menu(String[][] seats, boolean[][] sitIsTaken) {
        while (true) {
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    """);
            String input = scanner.nextLine();
            if (input.equals("1")) {
                printSeats(seats);
            } else if (input.equals("2")) {
                checkSeat(numberOfRows, numberOfSeatsInRow, sitIsTaken);
                getTicketPrice(numberOfRows);
                markSeat(seats, sitIsTaken, row, seat);
            } else if (input.equals("3")) {
                statistics();
            } else if (input.equals("0")) {
                break;
            }
            System.out.println();
        }
    }

    static void statistics() {
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        double percentage = calcPercentage();
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + getTotalIncome());

    }

    static double calcPercentage() {
        double percentage = (double) numberOfPurchasedTickets * 100 / numberOfTotalSeats;
        return percentage;
    }

    static int totalIncome = 0;

    static int getTotalIncome() {
        if (numberOfTotalSeats <= 60) {
            totalIncome = numberOfTotalSeats * 10;
        } else {
            int frontRows = numberOfRows / 2;
            int backRows = numberOfRows - frontRows;
            totalIncome = (frontRows * numberOfSeatsInRow * 10) + (backRows * numberOfSeatsInRow * 8);
        }
        return totalIncome;
    }

    static void checkSeat(int numberOfRows, int numberOfSeatsInRow, boolean[][] sitIsTaken) {
        while (true) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            if (row < 1 || row > numberOfRows) {
                System.out.println("Wrong input!");
                continue;
            }
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();
            if (seat < 1 || seat > numberOfSeatsInRow) {
                System.out.println("Wrong input!");
                continue;
            }
            scanner.nextLine();

            if (sitIsTaken[row - 1][seat - 1]) {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }
    }

    // 9
    // first half 4 -> 4 * 9 * 10$
    // back half 5 - > 5 * 9 * 8$

    // 10 dollar front
    // 8 dollar back
}