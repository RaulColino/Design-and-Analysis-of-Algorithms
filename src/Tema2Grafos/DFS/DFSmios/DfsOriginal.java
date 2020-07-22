package Tema2Grafos.DFS.DFSmios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DfsOriginal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        int nAristas = teclado.nextInt();
        int nodoInicial = teclado.nextInt();

        List<Integer>[] g = new List[nNodos];
        for (int i = 0; i < nNodos; i++) {
            g[i]= new ArrayList<>();
        }
        for (int i = 0; i < nAristas; i++) {
            int n1 = teclado.nextInt();
            int n2 = teclado.nextInt();
            g[n1].add(n2);
            g[n2].add(n1);
        }

        mostrarRecorridoDFS(g,nodoInicial);
    }

    private static void mostrarRecorridoDFS(List<Integer>[] g, int nodoInicial) {
        boolean[] visitados = new boolean[g.length];
        List<Integer> recorrido = new ArrayList<>();
        avanzarDFS(g,nodoInicial,recorrido,visitados);
        for (int i = 0; i < g.length; i++) {
            if(!visitados[i]){
            avanzarDFS(g,i,recorrido,visitados);
            }
        }
        System.out.println(recorrido);
    }

    private static void avanzarDFS(List<Integer>[] g, int nodo, List<Integer> recorrido, boolean[] visitados) {
        recorrido.add(nodo);
        visitados[nodo]=true;
        for (int ady: g[nodo]){
            if (!visitados[ady]){
                avanzarDFS(g,ady,recorrido,visitados);
            }
        }
    }

}
