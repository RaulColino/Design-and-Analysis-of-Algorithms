package Tema2Grafos.DFS;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    public static List<Integer> recorridoEnProfundidad(List<Integer>[] grafo, int verticeInicial) {  //Entra el grafo (Array de listas de adyacentes) y el vertice por el que empezará el recorrido
        int nNodosGrafo = grafo.length;                                                   //Conseguimos el numero de nodos del grafo para instanciar recorrido y visitados con el tamaño adecuado
        List<Integer> recorrido = new ArrayList<>(nNodosGrafo);
        boolean[] visitados =  new boolean[nNodosGrafo];                                  //grafo de 9 nodos p.ej -> visitados = boolean[9]
        avanzarRecursivamente(grafo,verticeInicial,recorrido,visitados);                  //Avanzamos al siguinte nodo a visitar
        return recorrido;
    }

    private static void avanzarRecursivamente(List<Integer>[] grafo, int vertice, List<Integer> recorrido, boolean[] visitados) {
        recorrido.add(vertice);              //Añadimos el  nodo visitado al recorrido
        visitados[vertice] = true;           //Marcamos como visitado el nodo en el array visitados
        for(int ady: grafo[vertice]){
            if(!visitados[ady]){
                avanzarRecursivamente(grafo,ady,recorrido,visitados);
            }
        }
    }
}
