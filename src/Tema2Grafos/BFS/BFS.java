package Tema2Grafos.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    //Método que devuelve el recorrido de un grafo en anchura dado un vertice inicial
    public static List<Integer> recorridoEnAnchura(List<Integer>[] grafo, int verticeInicial){ //grafo de 9 nodos, verticeInicial=1

        int longitudGrafo = grafo.length;                                                      //longitud grafo = 9+1
        List<Integer> recorrido = new ArrayList<>(longitudGrafo);                              //longitud lista recorrido = 9+1
        boolean[] visitados = new boolean[longitudGrafo];                                      //longitud array visitados = 9+1
        //cola de nodos pendientes para visitar a sus adyacentes
        Queue<Integer> cola = new LinkedList<>();

        //Ahora empieza el algoritmo de recorrido en anchura
        visitados[verticeInicial]= true;
        recorrido.add(verticeInicial);
        cola.add(verticeInicial);
        while(!cola.isEmpty()){                           //mientras haya algún nodo pendiente por visitar sus adyacentes...
            int aux = cola.remove();                      //saca a primero de la cola y lo mete en aux
            for (int adj: grafo[aux] ){                   //recorremos la lista de adyacentes del nodo aux
                if(!visitados[adj]){                      //si no ha sido visitado el nodo adyacente adj...
                    visitados[adj]=true;                  //se marca adj como visitado en el array visitados
                    recorrido.add(adj);                   //se añade adj como nodo siguiente en el recorrido
                    cola.add(adj);                        //se añade adj a la cola de nodos pendientes para visitar sus adyacentes
                }
            }
        }
        return recorrido;
    }


}
