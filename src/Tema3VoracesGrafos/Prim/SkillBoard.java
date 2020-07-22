package Tema3VoracesGrafos.Prim;
/*
5 6
1 2 5
1 3 2
2 4 1
3 4 3
3 5 5
4 5 3
 */

import java.util.*;

public class SkillBoard {
    // Hallar un Arbol de Recubrimiento Minimo (Minimum Spanning Tree)
    //Nodo 1 es el nodo inicial
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        List<Arista>[] grafo = new List[nNodos+1];
        for (int i = 1; i < nNodos+1; i++) {
            grafo[i]=new ArrayList<>();
        }
        int nAristas = teclado.nextInt();
        for (int i = 0; i < nAristas; i++) {
            int nodo1 = teclado.nextInt();
            int nodo2 = teclado.nextInt();
            int peso = teclado.nextInt();
            Arista a12 = new Arista(nodo1,nodo2,peso);
            Arista a21 = new Arista(nodo2,nodo1,peso);
            grafo[nodo1].add(a12);
            grafo[nodo2].add(a21);
        }
        mostrarCosteMinimoMST(grafo);
    }

    private static void mostrarCosteMinimoMST(List<Arista>[] grafo) {
        Set<Arista> aristasSol = prim(grafo);
        int costeMinimo = 0;
        for (Arista a: aristasSol) {
            costeMinimo += a.peso;
            /*System.out.printf("%2d, %2d, %2d \n",a.n1,a.n2,a.peso );*/
            System.out.println(a);
        }
        System.out.println(costeMinimo);
    }

    private static Set<Arista> prim(List<Arista>[] grafo) {
        Set<Arista> aristasSol = new HashSet<>();
        PriorityQueue<Arista>priorityQueue = new PriorityQueue<>();
        boolean[] visitados = new boolean[grafo.length]; //nNodos+1
        Arista aristaAux = new Arista(-1,1,0);
        priorityQueue.add(aristaAux);
        while(!priorityQueue.isEmpty()){
            Arista arista = priorityQueue.poll();
            if(!visitados[arista.n2]){
                visitados[arista.n2]=true;
                aristasSol.add(arista);
                for (Arista aristaAdy: grafo[arista.n2]) {
                    if(!visitados[aristaAdy.n2]){
                        priorityQueue.add(aristaAdy);
                    }
                }
            }
        }
        aristasSol.remove(aristaAux);
        return aristasSol;
    }

    private static class Arista implements Comparable<Arista>{
        public int n1, n2, peso;

        public Arista(int nodo1, int nodo2, int peso) {
            this.n1 = nodo1;
            this.n2 = nodo2;
            this.peso = peso;
        }
        @Override
        public int compareTo(Arista a) {
            return Integer.compare(peso,a.peso);
        }
        @Override
        public String toString() {
            return "Arista{" +
                    "n1=" + n1 +
                    ", n2=" + n2 +
                    ", peso=" + peso +
                    '}';
        }

    }
}
