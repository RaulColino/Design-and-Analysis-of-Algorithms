package Tema3VoracesGrafos.Dijkstra;

import java.util.*;
/*
5 7
1 2 5
1 3 2
2 4 1
3 4 3
3 5 5
4 5 3
5 1 3
 */

public class Dijkstra {
    //Hallar un camino que visite todos los nodos de coste minimo

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        List<Arista>[] grafo = new List[nNodos + 1];
        for (int i = 1; i < nNodos + 1; i++) {
            grafo[i] = new ArrayList<>();
        }
        int nAristas = teclado.nextInt();
        for (int i = 0; i < nAristas; i++) {
            int nodo1 = teclado.nextInt();
            int nodo2 = teclado.nextInt();
            int peso = teclado.nextInt();
            Arista a12 = new Arista(nodo1, nodo2, peso);
            Arista a21 = new Arista(nodo2, nodo1, peso);
            grafo[nodo1].add(a12);
            grafo[nodo2].add(a21);
        }
        mostrarCaminoMinimo(grafo, 1); //Muestra en un array las dist minimas a cada nodo
    }

    private static int[] mostrarCaminoMinimo(List<Arista>[] grafo, int nodoInicio) {
        boolean[] visitados = new boolean[grafo.length];//nNodos+1
        int[] previos = new int[grafo.length]; //nNodos+1
        Arrays.fill(previos, -1);
        int[] distancias = new int[grafo.length]; //nNodos+1
        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arista aristaAux = new Arista(-1, nodoInicio, 0);
        PriorityQueue<Arista> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(aristaAux);
        while (!priorityQueue.isEmpty()) {
            Arista arista = priorityQueue.poll();
            if (!visitados[arista.n2]) {
                visitados[arista.n2] = true;
                distancias[arista.n2] = arista.peso;
                previos[arista.n2] = arista.n1;
                for (Arista aristaAdy : grafo[arista.n2]) {
                    if(arista.peso+aristaAdy.peso < distancias[aristaAdy.n2]){
                        Arista arActualizada = new Arista(aristaAdy.n1,aristaAdy.n2,arista.peso+aristaAdy.peso);
                        priorityQueue.add(arActualizada);
                       // distancias[aristaAdy.n2]=arActualizada.peso; <- no hace falta
                    }
                }
            }
        }
        System.out.println(Arrays.toString(previos));
        System.out.println(Arrays.toString(distancias));
        return previos;
    }

    private static class Arista implements Comparable<Arista> {
        public int n1, n2, peso;

        public Arista(int n1, int n2, int peso) {
            this.n1 = n1;
            this.n2 = n2;
            this.peso = peso;
        }

        @Override
        public int compareTo(Arista a) {
            return Integer.compare(peso, a.peso);
        }
    }
}
