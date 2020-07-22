package Tema3VoracesGrafos.Dijkstra;

import java.util.*;
/*
5 6
1 2 5
1 3 2
2 4 1
3 4 3
3 5 5
4 5 3
 */
public class CaminoDeDistMin {
    //Camino de distancia minima de un nodo A a un nodo B

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        List<Arista>[] grafo = new List[nNodos+1];
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
        mostrarCaminoOptimo(1, 5, grafo);
    }

    private static int[] mostrarCaminoOptimo(int nodoIni, int nodoFin, List<Arista>[] grafo) {
        boolean[] visitados = new boolean[grafo.length];
        int[] previos = new int[grafo.length];
        Arrays.fill(previos, -1);
        int[] distancias = new int[grafo.length];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        PriorityQueue<Arista> priorityQueue = new PriorityQueue<>();
        Arista aristaAux = new Arista(-1, nodoIni, 0);
        priorityQueue.add(aristaAux);
        while (!priorityQueue.isEmpty()) {
            Arista arista = priorityQueue.poll();
            if (!visitados[arista.n2]) {
                visitados[arista.n2] = true;
                distancias[arista.n2] = arista.peso;
                previos[arista.n2] = arista.n1;
                if(arista.n2 == nodoFin){
                    System.out.println("Coste minimo: "+arista.peso);
                    break;
                }
                for (Arista aAdy : grafo[arista.n2]) {
                    if (arista.peso + aAdy.peso < distancias[aAdy.n2]) {
                        Arista aActualizada = new Arista(aAdy.n1,aAdy.n2,arista.peso+aAdy.peso);
                        priorityQueue.add(aActualizada);
                        distancias[aAdy.n2] = aActualizada.peso;
                    }
                }
            }
        }
        LinkedList<Integer> caminoOptimo = new LinkedList<>();
        int p = nodoFin;
        while(p!=-1){
            caminoOptimo.addFirst(p);
            p = previos[p];
        }
        System.out.println(caminoOptimo);
        return previos;
    }

    private static class Arista implements Comparable<Arista> {
        int n1, n2, peso;

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
