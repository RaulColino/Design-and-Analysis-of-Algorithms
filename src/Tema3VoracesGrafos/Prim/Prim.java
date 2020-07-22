package Tema3VoracesGrafos.Prim;

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

public class Prim {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();//
        List<Arista>[] grafo = new List[nNodos + 1];
        for (int i = 1; i < nNodos + 1; i++) {
            grafo[i] = new ArrayList<>();
        }
        int nAristas = teclado.nextInt();//
        for (int i = 0; i < nAristas; i++) {
            int nodo1 = teclado.nextInt();
            int nodo2 = teclado.nextInt();
            int peso = teclado.nextInt();

            Arista a12 = new Arista(nodo1, nodo2, peso);
            Arista a21 = new Arista(nodo2, nodo1, peso);
            grafo[nodo1].add(a12);
            grafo[nodo2].add(a21);
        }
        mostrarMST(grafo); //Minimum Spanning Tree
    }

    private static void mostrarMST(List<Arista>[] grafo) {
        Set<Arista> aristasSol = prim(1, grafo);
        int costeMinimo = 0;
        System.out.println();
        for (Arista a : aristasSol) {
            costeMinimo += a.getPeso();
            System.out.printf("%2d %2d %2d\n", a.getNodo1(), a.getNodo2(), a.getPeso());
        }
        System.out.println("Coste minimo: " + costeMinimo);

    }

    private static Set<Arista> prim(int nodoInicio, List<Arista>[] grafo) {
        Set<Arista> aristasSol = new HashSet<>();
        boolean visitados[] = new boolean[grafo.length]; //nNodos+1
        int costesOptimos[] = new int[grafo.length]; //Coste optimo para llegar a cada nodo desde un adyacente suyo
        Arrays.fill(costesOptimos, Integer.MAX_VALUE);
        Arista aristaAux = new Arista(-1, nodoInicio, 0);
        PriorityQueue<Arista> priorityQueue = new PriorityQueue<>(Comparator.comparing(arista -> arista.getPeso()));
        priorityQueue.add(aristaAux);
        while (!priorityQueue.isEmpty()) {
            Arista aristaNueva = priorityQueue.poll();
            if (!visitados[aristaNueva.getNodo2()]) {
                visitados[aristaNueva.getNodo2()] = true;
                aristasSol.add(aristaNueva);
                System.out.printf("%2d %2d %2d\n", aristaNueva.getNodo1(), aristaNueva.getNodo2(), aristaNueva.getPeso());
                for (Arista aristaAdy : grafo[aristaNueva.getNodo2()]) {
                    if (!visitados[aristaAdy.getNodo2()]) {
                        if (aristaAdy.getPeso() < costesOptimos[aristaAdy.getNodo2()]) { //Es necesaria esta sentencia?
                            costesOptimos[aristaAdy.getNodo2()]=aristaAdy.getPeso();
                            priorityQueue.add(aristaAdy);
                        }
                    }
                }
            }
        }
        aristasSol.remove(aristaAux);
        return aristasSol;
    }

    private static class Arista {
        private int nodo1, nodo2, peso;

        public Arista(int nodo1, int nodo2, int peso) {
            this.nodo1 = nodo1;
            this.nodo2 = nodo2;
            this.peso = peso;
        }

        public int getNodo1() {
            return nodo1;
        }

        public int getNodo2() {
            return nodo2;
        }

        public int getPeso() {
            return peso;
        }

        @Override
        public String toString() {
            return "Arista{" +
                    "nodo1=" + nodo1 +
                    ", nodo2=" + nodo2 +
                    ", peso=" + peso +
                    '}';
        }
    }

}
