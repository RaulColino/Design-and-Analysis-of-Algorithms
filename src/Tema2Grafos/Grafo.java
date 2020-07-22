package Tema2Grafos;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    public static List<Integer>[] crearGrafoNoDirigidoDisperso(){
        int nNodos = 9; //Grafo no dirigido disperso de 9 nodos (ejemplo de las diapositivas)
        List<Integer>[] grafo = new List[nNodos+1]; //Array de listas de Integer

        for(int i=1; i<=nNodos; i++){
            grafo[i] = new ArrayList<>(nNodos);
        }
        //Al ser un grafo no dirigido cada arista es añadida en las dos direcciones

        grafo[1].add(2); grafo[2].add(1);
        grafo[1].add(4); grafo[4].add(1);
        grafo[1].add(8); grafo[8].add(1);

        grafo[2].add(3); grafo[3].add(2);
        grafo[2].add(4); grafo[4].add(2);

        grafo[4].add(3); grafo[3].add(4);
        grafo[4].add(7); grafo[7].add(4);

        grafo[8].add(9); grafo[9].add(8);

        grafo[3].add(5); grafo[5].add(3);

        grafo[7].add(9); grafo[9].add(7);
        grafo[7].add(6); grafo[6].add(7);

        grafo[5].add(6); grafo[6].add(5);

        return grafo;
    }

    public static List<Integer>[] crearGrafoDirigidoDisperso(){

        int nNodos = 8;
        List<Integer>[] grafo = new List[nNodos+1];

        for(int i=1;i<=nNodos;i++){
            grafo[i] = new ArrayList<>();
        }

        // Cda arista es añadida en en una sola direccion al ser un grafo dirigido

        grafo[1].add(2);
        grafo[1].add(4);
        grafo[1].add(8);
        grafo[2].add(3);
        grafo[3].add(1);
        grafo[4].add(8);
        grafo[5].add(2);
        grafo[5].add(6);
        grafo[6].add(5);
        grafo[6].add(2);
        grafo[6].add(3);
        grafo[7].add(4);
        grafo[8].add(7);


        return grafo;
    }

    public int[][] crearGrafoNoDirigidoDenso(){

        return null;
    }

    public int[][] crearGrafoDirigidoDenso(){

        return null;
    }

    public List<Integer>[] recorrerGrafoEnProfundidad(){


        return null;
    }

}
