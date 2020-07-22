package Tema5Backtracking.NReinas;

import java.util.Scanner;

public class NReinas {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int tamTablero = teclado.nextInt();
        resolverNreinas(tamTablero);
    }

    private static void resolverNreinas(int tamTablero) {
        int[][] tablero = new int[tamTablero][tamTablero];
        boolean tieneSol = hallarSol(tablero, 0);
        if (!tieneSol){
            System.out.println("Solucion no encontrada");
        }
    }

    private static boolean hallarSol(int[][] tablero, int colActual) {
        //caso base (todas las reinas estan puestas)
        if (colActual >= tablero.length) {
            printSol(tablero);
            return true;
        } else {
            for (int i = 0; i < tablero.length; i++) {
                if (esPosicionValida(tablero, i, colActual)) {
                    tablero[i][colActual] = 1;
                    //si a partir de este punto llegamos a una solucion devolvemos true
                    if (hallarSol(tablero, colActual + 1) == true) {
                        return true;
                    }
                    tablero[i][colActual] = 0;
                }
            }
            return false;
        }
    }


    private static void printSol(int tablero[][]) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(tablero[i][j] + "  ");
            }
            System.out.println();
        }
    }

    //Comprueba las reinas de la zona de la izquierda
    private static boolean esPosicionValida(int[][] tablero, int fila, int col) {
        //comprobamos si hay reinas en la fila
        for (int j = 0; j < col; j++) {
            if (tablero[fila][j] == 1) {
                return false;
            }
        }
        //comprobamos la diagonal que baja hacia la izq desde la posicion
        for (int i = fila, j = col; j >= 0 && i < tablero.length; i++, j--) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }
        //comprobamos la diagonal que sube hacia la izq desde la posicion
        for (int i = fila, j = col; i >= 0 && j >= 0; i--, j--) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }
        return true;
    }


}
