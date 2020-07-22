package Tema5Backtracking.Sudoku;

import java.util.Arrays;
import java.util.Scanner;

/*
        5 3 0 0 7 0 0 0 0
        6 0 0 1 9 5 0 0 0
        0 9 8 0 0 0 0 6 0
        8 0 0 0 6 0 0 0 3
        4 0 0 8 0 3 0 0 1
        7 0 0 0 2 0 0 0 6
        0 6 0 0 0 0 2 8 0
        0 0 0 4 1 9 0 0 5
        0 0 0 0 8 0 0 7 9
        */
public class Sudoku {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int[][] sudoku = new int[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                sudoku[fila][col] = teclado.nextInt();
            }
        }
        resolverSudoku(sudoku);
    }

    public static void resolverSudoku(int[][] sudoku) {
        boolean resul = solve(sudoku);
        if (resul == false) {
            System.out.println("Sin solucion");
        } else {
            printSudoku(sudoku);
        }
    }

    private static boolean solve(int[][] sudoku) {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (sudoku[f][c] == 0) {
                    for (int n = 1; n < 10; n++) {
                        if (esValido(n, f, c, sudoku)) {
                            sudoku[f][c] = n;
                            if (solve(sudoku)) {
                                return true;
                            }
                            sudoku[f][c] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean esValido(int num, int fila, int col, int[][] sudoku) {
        return (validoEnFila(num, fila, sudoku) && validoEnColumna(num, col, sudoku) && validoEnSeccion(num, fila, col, sudoku));
    }


    private static boolean validoEnFila(int num, int fila, int[][] sudoku) {
        for (int c = 0; c < 9; c++) {
            if (sudoku[fila][c] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean validoEnColumna(int num, int col, int[][] sudoku) {
        for (int f = 0; f < 9; f++) {
            if (sudoku[f][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean validoEnSeccion(int num, int fila, int col, int[][] sudoku) {
        int f = fila - fila % 3;
        int c = col - col % 3;
        for (int i = f; i < f + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (sudoku[i][j] == num)
                    return false;
            }
        }
        return true;
    }

    private static void printSudoku(int[][] sudoku) {
        for (int f = 0; f < 9; f++) {
            System.out.println(Arrays.toString(sudoku[f]));
        }
        System.out.println();
    }

}
