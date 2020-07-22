package Tema3VoracesGrafos;
/*
4
6
2 0 28
0 1 35
1 2 89
0 3 72
1 2 16
0 1 43
3
1
2 0 748
0
*/


import java.util.*;

//Prim -> pero con nodos de 0-inf en vez de 1-inf y hallar el arbol de coste maximo (con el minimo numero de aristas)
public class calleCara {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nextValue;
        while ((nextValue = teclado.nextInt()) != 0) {
            int nNodos = nextValue;
            List<Arista>[] g = new List[nNodos]; // grafo de 0 a nNodos
            for (int i = 0; i < nNodos; i++) {
                g[i] = new ArrayList<>();
            }
            int nAristas = teclado.nextInt();
            for (int i = 0; i < nAristas; i++) {
                int n1 = teclado.nextInt();
                int n2 = teclado.nextInt();
                int peso = teclado.nextInt();
                Arista a12 = new Arista(n1, n2, peso);
                Arista a21 = new Arista(n2, n1, peso);
                g[n1].add(a12);
                g[n2].add(a21);
            }
            mostrarCosteMaximo(g); //muestra nIslas, nCarreteras y costeMaximo
        }
    }

    private static void mostrarCosteMaximo(List<Arista>[] g) {
        int nIslas = 0;
        boolean[] visitados = new boolean[g.length]; //nNodos = g.length
        Set<Arista> aristasSol = new HashSet<>();
        for (int nodo = 0; nodo < g.length; nodo++) { //empezamos a explorar por el nodo 0. Si queda alguno sin explorar añadimos las aristas de coste maximo que quedan
            if (!visitados[nodo]){
                aristasSol.addAll(primMaximumSpanningTree(nodo, g, visitados));
                nIslas++;
            }
        }
        System.out.print("nIslas: "+nIslas);
        int costeMaximo = 0;
        for (Arista a : aristasSol) {
            costeMaximo += a.peso;
        }
        System.out.print(" nCarreterasTope: "+aristasSol.size());
        System.out.println(" costeMax: "+costeMaximo);
        System.out.println("---");
    }

    private static Set<Arista> primMaximumSpanningTree(int nodoInicio, List<Arista>[] g, boolean[] visitados) {
        Set<Arista> aristasSol = new HashSet<>();
        int[] costesOptimosMaximos = new int[g.length]; //nNodos
        Arrays.fill(costesOptimosMaximos, 0);
        PriorityQueue<Arista> priorityQueue = new PriorityQueue<>();
        Arista aristaAux = new Arista(-1, nodoInicio, 0);
        priorityQueue.add(aristaAux);
        while (!priorityQueue.isEmpty()) {
            Arista arista = priorityQueue.poll();
            if (!visitados[arista.n2]) {
                visitados[arista.n2] = true;
                aristasSol.add(arista);
                for (Arista aristaAdy : g[arista.n2]) {
                    if (!visitados[aristaAdy.n2]) { //para los nodos ady no visitados
                        if (aristaAdy.peso > costesOptimosMaximos[aristaAdy.n2]) { //si el coste para llegar al ady es mayor q el conocido por ahora
                            costesOptimosMaximos[aristaAdy.n2] = aristaAdy.peso; //actualizamos el coste maximo para evitar meter aristas de coste menor que conecten al nodo ady y asi ahorrar tiempo de computo
                            priorityQueue.add(aristaAdy); //añadimos la arista ady nueva
                        }
                    }
                }
            }
        }
        aristasSol.remove(aristaAux);
        return aristasSol;
    }

    private static class Arista implements Comparable<Arista> {

        public int n1;
        public int n2;
        public int peso;

        public Arista(int n1, int n2, int peso) {
            this.n1 = n1;
            this.n2 = n2;
            this.peso = peso;
        }

        @Override
        public int compareTo(Arista o) {
            if (peso > o.peso) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
