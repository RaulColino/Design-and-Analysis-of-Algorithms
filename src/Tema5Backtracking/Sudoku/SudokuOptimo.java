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

public class SudokuOptimo {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int[][] sudoku = new int[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                sudoku[fila][col] = teclado.nextInt();
            }
        }
        SudokuOptimo s = new SudokuOptimo();
        s.resolverSudoku(sudoku);
    }

    public void resolverSudoku(int[][] sudoku) {
        Posicion p = new Posicion(0, 0);
        if (!resolver(sudoku, p)) {
            System.out.println("sin solucion");
        } else {
            System.out.println(Arrays.deepToString(sudoku));
        }
    }

    private boolean resolver(int[][] sudoku, Posicion p) {
        //Si estamos ante una celda vacia
        if (sudoku[p.getF()][p.getC()] == 0) {
            for (int n = 1; n <= 9; n++) {
                if (esValido(n, p.getF(), p.getC(), sudoku)) {
                    sudoku[p.getF()][p.getC()] = n;
                    if (resolver(sudoku, pSiguiente(p))) {
                        return true;
                    } else {
                        sudoku[p.getF()][p.getC()] = 0;
                        //continuamos el 'for' probando valores
                    }
                }
            }
            return false;
        }
        //Si estamos ante una celda ocupada y...
        //si hemos llegado al final
        if (p.getF() == 8 && p.getC() == 8) {
            System.out.println("solucion:");
            System.out.println(Arrays.deepToString(sudoku));
            return true;
        }
        //si no hemos llegado al final continuamos en la siguiente posicion
        return resolver(sudoku, pSiguiente(p));
    }


    private boolean esValido(int num, int fila, int col, int[][] sudoku) {
        return (validoEnFila(num, fila, sudoku) && validoEnColumna(num, col, sudoku) && validoEnCelda(num, fila, col, sudoku));
    }


    private boolean validoEnFila(int num, int fila, int[][] sudoku) {
        for (int c = 0; c < 9; c++) {
            if (sudoku[fila][c] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean validoEnColumna(int num, int col, int[][] sudoku) {
        for (int f = 0; f < 9; f++) {
            if (sudoku[f][col] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean validoEnCelda(int num, int fila, int col, int[][] sudoku) {
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

    private Posicion pSiguiente(Posicion p) {
        int f = p.getF();
        int c = p.getC();
        if (p.getC() == 8) {
            p.setF(f + 1);
            p.setC(0);
        } else {
            p.setC(c + 1);
        }
        return p;
    }

    private static void printSudoku(int[][] sudoku) {
        for (int f = 0; f < 9; f++) {
            System.out.println(Arrays.toString(sudoku[f]));
        }
        System.out.println();
    }

    private class Posicion {
        int f;
        int c;

        public Posicion(int f, int c) {
            this.f = f;
            this.c = c;
        }

        public int getF() {
            return f;
        }

        public void setF(int f) {
            this.f = f;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }
    }
}
