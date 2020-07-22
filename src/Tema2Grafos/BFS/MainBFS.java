package Tema2Grafos.BFS;

import Tema2Grafos.Grafo;

import java.util.List;

public class MainBFS {
    public static void main(String args[]){
        //El grafo es un array de listas de Integer y no de conjuntos ya que un nodo puede tenerse como adyacente a si mismo 1->1
        List<Integer>[] grafo = Grafo.crearGrafoNoDirigidoDisperso(); //Grafo de las diapositivas de 9 nodos
        List<Integer> recorrido = BFS.recorridoEnAnchura(grafo,1); //Devuelve un arrayList de los nodos visitados en orden
        System.out.println(recorrido);
    }

}
