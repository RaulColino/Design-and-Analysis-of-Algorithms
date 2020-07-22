package Tema3VoracesGrafos.Dijkstra;


import java.util.*; //Dijkstra, ya que devuelve una ruta
/*
1 6 10
6 8
8 3 2 1 4 9
1 2
1 3
2 4
3 4
3 5
4 5
4 6
5 6
*/

public class Blaster {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int posTirador = teclado.nextInt();
        int posObjetivo = teclado.nextInt();
        int potencia = teclado.nextInt();

        int nNodos = teclado.nextInt();
        int nAristas = teclado.nextInt();
        List<Arista>[] g = new List[nNodos+1];
        for (int i = 1; i < nNodos+1; i++) {
            g[i]= new ArrayList<>();
        }

        int[] pesos = new int[nNodos+1];
        for (int i = 1; i < nNodos+1; i++) {
            pesos[i] = teclado.nextInt();
        }

        for (int i = 0; i < nAristas; i++) {
            int n1 = teclado.nextInt();
            int n2 = teclado.nextInt();
            Arista a12 = new Arista(n1, n2, pesos[n2]);
            Arista a21 = new Arista(n2, n1, pesos[n1]);
            g[n1].add(a12);
            g[n2].add(a21);
        }

        resolver(g, posTirador, posObjetivo,potencia);
    }

    private static void resolver(List<Arista>[] g, int posTirador, int posObjetivo, int potencia) {
        res(g, posTirador, posObjetivo,potencia);
    }

    private static void res(List<Arista>[] g, int posTirador, int posObjetivo, int potencia) {
        boolean visitados[] = new boolean[g.length]; //(tamaño nNodos+1) array de booleanos de nodos visitados
        int nodosPrevios[] = new int[g.length];//(tamaño nNodos+1) contiene el nodo previo de cada nodo en nuestro camino. Sirve para poder imprimir el camino por pantalla
        Arrays.fill(nodosPrevios, -1);
        int[] costesDistancias = new int[g.length];//(tamaño nNodos+1) distancia mas corta a cada nodo en este momento n+1
        Arrays.fill(costesDistancias, Integer.MAX_VALUE);
        PriorityQueue<Arista> priorityQueue = new PriorityQueue<>();
        Arista aristaAux = new Arista(-1, posTirador, 0);//arista auxiliar para comenzar
        priorityQueue.add(aristaAux);
        while (!priorityQueue.isEmpty()) {
            Arista aNueva = priorityQueue.poll();
            if (!visitados[aNueva.n2]) {
                visitados[aNueva.n2] = true;
                costesDistancias[aNueva.n2] = aNueva.peso; //asignamos el nuevo coste/distancia mas corta al nodo aNueva.n2
                nodosPrevios[aNueva.n2] = aNueva.n1;
                if (aNueva.n2 == posObjetivo) {//si hemos llegado al final
                    int potenciaFinal = potencia-aNueva.peso;
                    if (potenciaFinal <= 0){
                        System.out.println(0);
                    }else{
                        System.out.println(potenciaFinal);
                    }
                    break;
                }
                for (Arista aristaAdy : g[aNueva.n2]) {
                    if (aNueva.peso + aristaAdy.peso < costesDistancias[aristaAdy.n2]) {
                        if (aristaAdy.n2 == posObjetivo) { // Aqui es donde esta la diferencia respecto al Dijkstra: no se tiene en cuenta el peso de la arista final del camino
                            Arista aristaAdyPesoAcumulado = new Arista(aristaAdy.n1, aristaAdy.n2, aNueva.peso);
                            priorityQueue.add(aristaAdyPesoAcumulado);
                        }else{
                            Arista aristaAdyPesoAcumulado = new Arista(aristaAdy.n1, aristaAdy.n2, aNueva.peso+aristaAdy.peso);
                            priorityQueue.add(aristaAdyPesoAcumulado);
                        }

                    }
                }
            }
        }
        //Esto ya no hay que hacerlo pero lo pongo para mostrar el camino
        LinkedList<Integer> caminoOptimo = new LinkedList<>();
        int nodoActual = posObjetivo; // si no se ha llegado a la posObjetivo porque esta aislado el camino solo contendria el nodo posObjetivo ya que su previo por defecto es -1
        while (nodoActual!=-1){
            caminoOptimo.addFirst(nodoActual);
            nodoActual = nodosPrevios[nodoActual];
        }
        System.out.println(caminoOptimo);
    }


    private static class Arista implements Comparable<Arista> {
        int n1;
        int n2;
        int peso;

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

