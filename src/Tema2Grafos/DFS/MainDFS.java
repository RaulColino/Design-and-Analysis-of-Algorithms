package Tema2Grafos.DFS;

import Tema2Grafos.Grafo;

import java.util.List;

public class MainDFS {
    public static void main(String[] args) {

        List<Integer>[] grafo = Grafo.crearGrafoNoDirigidoDisperso();
        List<Integer> recorrido = DFS.recorridoEnProfundidad(grafo, 1);
        System.out.println(recorrido);

    }


}
