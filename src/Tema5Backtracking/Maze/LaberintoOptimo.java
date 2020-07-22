package Tema5Backtracking.Maze;

import java.util.Arrays;
import java.util.Scanner;
/*  devuelve solucion con el minimo de pasos posible + representacion grafica
6
  0  0  0  0 -1  0
 -1 -1 -1  0  0  0
  0  0 -1  0  0  0
  0 -1  0  0 -1 -1
 -1  0  0 -1  0  0
  0  0  0  0  0  0
        */

public class LaberintoOptimo {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int n = teclado.nextInt();
        int[][] laberinto = new int[n][n];    //0,0 = esquina sup izq    i = eje y   j = eje x
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                laberinto[i][j] = teclado.nextInt();
            }
        }
        SolucionLaberinto solLab = new SolucionLaberinto(laberinto, n - 1);
        LaberintoOptimo lo = new LaberintoOptimo();
        SolucionLaberinto l = lo.hallarSolucion(solLab);
        if (l.getSolActual() == n * n) {
            System.out.println("SIN SOLUCION");
        } else {
            l.mostrarSolucion();
        }

    }

    private SolucionLaberinto hallarSolucion(SolucionLaberinto l) {
        int[][] labProvisional = l.getLaberintoSol().clone();
        return resolver(l, labProvisional, 0, 0, 0);
    }


    private SolucionLaberinto resolver(SolucionLaberinto l, int[][] tempMaze, int y, int x, int contador) {
        //Si estamos sobre un muro o paso retrocedemos
        if (tempMaze[y][x] == -1 || tempMaze[y][x] == 1) {
            System.out.println("muro o paso");
            return l;
        }
        // marcamos el paso dado
        contador++;
        System.out.println("Contador: " + contador);
        tempMaze[y][x] = 1;
        for (int i = 0; i <= l.getN(); i++) {
            System.out.println(Arrays.toString(tempMaze[i]));
        }
        // Si estamos en un punto a partir del cual no merece la pena seguir
        if (contador > l.getSolActual()) {
            System.out.println("No merece la pena seguir");
            tempMaze[y][x]=0;
            return l;
        }
        //Si hemos llegado a la meta
        if (x == l.getN() && y == l.getN()) {
            if (contador < l.getSolActual()) {
                l.setSolActual(contador);

                System.out.println("Solucion!!!!!!!!!!!!!!!!!!!!!!!!!!:");
                for (int i = 0; i <= l.getN(); i++) {
                    System.out.println(Arrays.toString(tempMaze[i]));
                }
                l.setLaberintoSol(tempMaze);
                tempMaze[y][x]=0;
                return l;
            }
            tempMaze[y][x]=0;
            return l;
        }
        //Si no hemos llegado a la meta avanzamos
        //der
        if (x + 1 <= l.getN()) {
            System.out.println("der");
            l = resolver(l, tempMaze, y, x + 1, contador);
        }
        //abj
        if (y + 1 <= l.getN()) {
            System.out.println("agj");
            l = resolver(l, tempMaze, y + 1, x, contador);
        }
        //izq
        if (x - 1 >= 0) {
            System.out.println("izq");
            l = resolver(l, tempMaze, y, x - 1, contador);
        }
        //arr
        if (y - 1 >= 0) {
            System.out.println("arr");
            l = resolver(l, tempMaze, y - 1, x, contador);
        }

        System.out.println("retrocedemos");
        for (int i = 0; i <= l.getN(); i++) {
            System.out.println(Arrays.toString(tempMaze[i]));
        }
        tempMaze[y][x]=0;
        return l;
    }
}

class SolucionLaberinto {
    private int n;
    private int solActual;
    private int[][] laberintoSol;

    public void setSolActual(int solActual) {
        this.solActual = solActual;
    }

    public SolucionLaberinto(int[][] laberinto, int n) {
        this.laberintoSol = laberinto;
        this.n = n;
        solActual = n * n;
    }

    public int getN() {
        return n;
    }

    public int getSolActual() {
        return solActual;
    }

    public int[][] getLaberintoSol() {
        return laberintoSol;
    }

    public void setLaberintoSol(int[][] laberintoSol) {
        for (int fila = 0; fila <= n; fila++) {
            this.laberintoSol[fila] = laberintoSol[fila].clone();
        }
    }

    public void mostrarSolucion() {
        System.out.println("El numero minimo de pasos es: " + solActual);
        for (int i = 0; i <= n; i++) {
            System.out.println(Arrays.toString(laberintoSol[i]));
        }
    }
}

