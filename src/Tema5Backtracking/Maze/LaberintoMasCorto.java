package Tema5Backtracking.Maze;

import java.util.Arrays;
import java.util.Scanner;

//En este problema se pide encontrar el número de celdas mínimo que hay que recorrer para
//conseguir escapar de un laberinto. Es importante tener en cuenta que la entrada será siempre la
//casilla (0,0) y la salida será siempre la casilla (𝑛 − 1, 𝑛 − 1).
        /*
6
  0  0  0  0 -1  0
 -1 -1 -1  0  0  0
  0  0 -1  0  0  0
  0 -1  0  0 -1 -1
 -1  0  0 -1  0  0
  0  0  0  0  0  0
        */
public class LaberintoMasCorto {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int n = teclado.nextInt();
        int[][] maze = new int[n][n];  //0,0 es la esquina superior izquierda
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = teclado.nextInt();
            }
        }
        LaberintoMasCorto l = new LaberintoMasCorto();
        int minPasos = l.resolverLaberinto(maze, 0, n * n, 0, 0, n - 1);
        if (minPasos == 25) {
            System.out.println("SIN SOLUCION");
        } else {
            System.out.println(minPasos);
        }
    }

    private int resolverLaberinto(int[][] maze, int contador, int solActual, int y, int x, int n) {
        // si nos encontramos ante un muro (=> -1)  o  paso (=> 1) o no merece la pena seguir porque no vamos a mejorar solActual
        if (maze[y][x] == -1 || maze[y][x] == 1 || contador + 1 == solActual) {
            return -1;
        }
        // si nos encontamos sobre una celda valida
        maze[y][x] = 1;
        contador++;
        //Si nos encontramos en la meta
        if (y == n && x == n) {

            System.out.println("meta pasos: " + contador + " pos y:" + y + " pos x:" + x);
            printMaze(maze, n);
            maze[y][x] = 0;
            return contador;
        }
        //En caso contrario
        //go der
        if (x + 1 <= n) {
            int result = resolverLaberinto(maze, contador, solActual, y, x + 1, n);
            if (result < solActual && result != -1) {
                solActual = result;
            }
        }
        //go abj
        if (y + 1 <= n) {
            int result = resolverLaberinto(maze, contador, solActual, y + 1, x, n);
            if (result < solActual && result != -1) {
                solActual = result;
            }
        }
        //go arr
        if (y - 1 >= 0) {
            int result = resolverLaberinto(maze, contador, solActual, y - 1, x, n);
            if (result < solActual && result != -1) {
                solActual = result;
            }
        }
        //go izq
        if (x - 1 >= 0) {
            int result = resolverLaberinto(maze, contador, solActual, y, x - 1, n);
            if (result < solActual && result != -1) {
                solActual = result;
            }
        }
        maze[y][x] = 0;
        return solActual;
    }

    private void printMaze(int[][] maze, int n) {
        for (int i = 0; i <= n; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }
    }
}
