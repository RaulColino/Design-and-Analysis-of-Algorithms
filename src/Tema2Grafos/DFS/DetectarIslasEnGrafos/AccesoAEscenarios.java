package Tema2Grafos.DFS.DetectarIslasEnGrafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
6 6
0 1
0 2
1 2
3 4
3 5
4 5
*/
public class AccesoAEscenarios {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        List<Integer>[] grafo = new List[nNodos];
        for (int i = 0; i < nNodos; i++) {
            grafo[i] = new ArrayList<>();
        }
        int nAristas = teclado.nextInt();
        for (int i = 0; i < nAristas; i++) {
            int n1 = teclado.nextInt();
            int n2 = teclado.nextInt();
            grafo[n1].add(n2);
            grafo[n2].add(n1);
        }
        mostrarNumIslasDFS(grafo);
    }

    private static void mostrarNumIslasDFS(List<Integer>[] grafo) {
        int n = grafo.length;
        boolean[] visitados = new boolean[n];
        int nIslas = 0;
        for (int nodo = 0; nodo < n; nodo++) {
            if (!visitados[nodo]) {
                dfs(grafo, nodo, visitados, nIslas);
                nIslas++;
            }
        }
        System.out.println(nIslas);

    }

    private static void dfs(List<Integer>[] grafo, int nodo, boolean[] visitados, int nIslas) {
        visitados[nodo] = true;
        for (int ady : grafo[nodo]) {
            if (!visitados[ady]) {
                dfs(grafo, ady, visitados, nIslas);
            }
        }
    }
}
