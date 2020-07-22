package Tema5Backtracking.ColoreadoGrafos;
/*
5 4 2
0 1
0 2
2 3
2 4
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class coloreadoGrafos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        List<Integer>[] grafo = new List[nNodos];
        for (int i = 0; i < nNodos; i++) {
            grafo[i] = new ArrayList<>();
        }
        int nAristas = teclado.nextInt();
        int nColores = teclado.nextInt();
        for (int i = 0; i < nAristas; i++) {
            int nodo1 = teclado.nextInt();
            int nodo2 = teclado.nextInt();
            grafo[nodo1].add(nodo2);
            grafo[nodo2].add(nodo1);
        }
        mostrarSoluciones(grafo, nNodos, nColores);

    }

    private static void mostrarSoluciones(List<Integer>[] grafo, int nNodos, int nColores) {
        int[] sol = new int[nNodos];
        int nSol = 0;
        nSol = colorearGrafo(grafo, 0, nColores, sol, nSol);
        if (nSol==0){
            System.out.println("No hay suficientes");
        }else {
            System.out.println("OK. Num de soluciones = "+nSol);
        }
    }

    private static int colorearGrafo(List<Integer>[] grafo, int pos, int nColores, int[] sol, int nSol) {
        sol[pos] = 0;
        for (int color = 1; color <= nColores; color++) {
            sol[pos] = color;
            if (esColorValido(grafo, pos, sol)) { //si es valido el color q acabamos de asignar
                if (pos == sol.length - 1) {
                    printSol(sol);
                    nSol++;
                } else {
                    nSol = colorearGrafo(grafo, pos + 1, nColores, sol, nSol);
                }
            }
            sol[pos] = 0;
        }
        return nSol;
    }

    private static void printSol(int[] sol) {
        for (int i = 0; i < sol.length; i++) {
            System.out.print(i + ":" + sol[i] + " ");
        }
        System.out.println();
    }

    private static boolean esColorValido(List<Integer>[] grafo, int pos, int[] sol) {
        for (int adj : grafo[pos]) {
            if (sol[adj] == sol[pos]) {
                return false;
            }
        }
        return true;
    }


}
