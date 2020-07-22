package Tema2Grafos.DFS.DFSmios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
6 6 3
0 1
0 2
1 2
3 4
3 5
4 5
 */

public class DfsMasIslas {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        int nAristas = teclado.nextInt();
        int nodoInicial = teclado.nextInt();

        List<Integer>[] g = new List[nNodos];
        for (int i = 0; i < nAristas; i++) {
            g[i]=new ArrayList<>();
        }
        for (int i = 0; i < nAristas; i++) {
            int n1 = teclado.nextInt();
            int n2 = teclado.nextInt();
            g[n1].add(n2);
            g[n2].add(n1);
        }

        mostrarRecorridoDFSyNnodos(g,nodoInicial);
    }

    private static void mostrarRecorridoDFSyNnodos(List<Integer>[] g, int nodoInicial) {
        List<Integer> recorrido = new ArrayList<>();
        boolean[] visitados = new boolean[g.length];
        int nIslas = 0;
        avanzarDFS(g,nodoInicial,recorrido,visitados);
        if (!recorrido.isEmpty()) nIslas++;
        for (int i = 0; i < g.length; i++) {
            if (!visitados[i]){
                avanzarDFS(g,i,recorrido,visitados);
                nIslas++;
            }
        }
        System.out.println(recorrido);
        System.out.println("nIslas: "+nIslas);
    }

    private static void avanzarDFS(List<Integer>[] g, int nodo, List<Integer> recorrido, boolean[] visitados) {
        recorrido.add(nodo);
        visitados[nodo] = true;
        for (int ady: g[nodo]) {
            if (!visitados[ady]){
                avanzarDFS(g,ady,recorrido,visitados);
            }
        }
    }
}
