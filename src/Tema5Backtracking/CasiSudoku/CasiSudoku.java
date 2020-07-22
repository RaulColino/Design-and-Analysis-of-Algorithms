package Tema5Backtracking.CasiSudoku;

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

3 0 6 0 2 5 7 0 9
0 8 0 0 0 0 3 0 4
1 9 0 4 0 7 0 2 8
6 2 1 0 0 0 5 8 0
8 0 7 1 0 0 0 9 0
0 3 0 0 0 0 2 6 0
0 0 3 2 0 6 1 0 0
5 0 4 9 0 1 0 3 0
0 0 0 0 0 0 6 7 0

0 3 2 9 0 8 0 1 7
0 1 7 0 3 4 8 0 6
6 4 0 7 2 1 9 5 3
1 8 6 4 0 0 3 7 0
2 0 9 3 8 5 6 4 1
3 5 4 6 1 7 2 8 9
8 2 3 1 5 0 0 0 4
4 6 0 0 0 0 0 0 0
0 9 0 0 4 0 0 0 0





        */

public class CasiSudoku {

    private int[][] sudokuSol = new int[9][9];

    private void comprobarSudoku(int[][] sudoku) {
        int resultado = resolver(sudoku);
        if (resultado == 0) {
            System.out.println("imposible");
        } else if (resultado == 1) {
            printSudoku(sudokuSol);
        } else {
            System.out.println("casi sudoku");
        }
    }

    // devuelve o si no hay sol, 1 si hay una sol, 2 si hay varias
    private int resolver(int[][] sudoku) {
        int numSol = 0;
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (sudoku[f][c] == 0) {
                    for (int n = 1; n < 10; n++) {
                        if (esValido(n, f, c, sudoku)) {
                            sudoku[f][c] = n;
                            numSol = resolver(sudoku) + numSol;
                            if (numSol > 1) {
                                return 2;
                            }
                            sudoku[f][c] = 0;
                        }
                    }
                    return numSol;
                }
            }
        }
        asignarSolucion(sudoku);
        return numSol + 1;
    }

    private boolean esValido(int n, int f, int c, int[][] sudoku) {
        return (validoEnFila(n, f, sudoku) && validoEnCol(n, c, sudoku) && ValidoEnSeccion(n, f, c, sudoku));
    }

    private boolean validoEnFila(int n, int f, int[][] sudoku) {
        for (int c = 0; c < 9; c++) {
            if (sudoku[f][c] == n) {
                return false;
            }
        }
        return true;
    }

    private boolean validoEnCol(int n, int c, int[][] sudoku) {
        for (int f = 0; f < 9; f++) {
            if (sudoku[f][c] == n) {
                return false;
            }
        }
        return true;
    }

    private boolean ValidoEnSeccion(int n, int f, int c, int[][] sudoku) {
        int inifila = f - f % 3;
        int iniCol = c - c % 3;
        for (int i = inifila; i < inifila + 3; i++) {
            for (int j = iniCol; j < iniCol + 3; j++) {
                if (sudoku[i][j] == n)
                    return false;
            }
        }
        return true;
    }


    private void printSudoku(int[][] sudoku) {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(sudoku[f][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void asignarSolucion(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuSol[i][j] = sudoku[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int[][] sudoku = new int[9][9];
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                sudoku[f][c] = teclado.nextInt();
            }
        }
        CasiSudoku c = new CasiSudoku();
        c.comprobarSudoku(sudoku);
    }

}
