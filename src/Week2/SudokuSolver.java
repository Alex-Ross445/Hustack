package Week2;


//Week 2 - Count number of sudoku solutions
//        Description
//        Write a program to compute the number of sudoku solutions (fill the zero elements of a given partial sudoku table)
//        Fill numbers from 1, 2, 3, .., 9 to 9 x 9 table so that:
//        Numbers of each row are distinct
//        Numbers of each column are distinct
//        Numbers on each sub-square 3 x 3 are distinct
//        Input
//        Each line i (i = 1, 2, ..., 9) contains elements of the i
//        th
//        row of the Sudoku table: elements are numbers from 0 to 9 (value 0 means the empty cell of the table)
//        Output
//        Write the number of solutions found
//
//        Example
//        Input
//        0 0 3 4 0 0 0 8 9
//        0 0 6 7 8 9 0 2 3
//        0 8 0 0 2 3 4 5 6
//        0 0 4 0 6 5 0 9 7
//        0 6 0 0 9 0 0 1 4
//        0 0 7 2 0 4 3 6 5
//        0 3 0 6 0 2 0 7 8
//        0 0 0 0 0 0 0 0 0
//        0 0 0 0 0 0 0 0 0
//        Output
//        64


import java.util.Scanner;

public class SudokuSolver {
    static final int SIZE = 9; // Size of the Sudoku grid
    static int solutionCount = 0; // Counter for valid solutions

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[SIZE][SIZE];

        // Read the Sudoku grid from input
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        // Solve the Sudoku and count the solutions
        solveSudoku(board);

        // Output the number of solutions found
        System.out.println(solutionCount);
        scanner.close();
    }

    private static void solveSudoku(int[][] board) {
        // Find the next empty cell (represented by 0)
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    // Try placing numbers from 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // Place the number
                            solveSudoku(board); // Recur to fill in the next cell
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return; // Exit the function when an empty cell is found
                }
            }
        }
        // If no empty cell is found, we've reached a valid solution
        solutionCount++;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check the row
        for (int j = 0; j < SIZE; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }
        // Check the column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // Check the 3x3 sub-grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true; // Valid placement
    }
}