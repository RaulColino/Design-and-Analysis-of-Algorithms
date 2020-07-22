package Tema5Backtracking.CiclosHamiltonianos;

/*
11 16
7 8
6 7
9 1
8 0
5 10
10 9
3 0
4 0
5 0
3 1
1 4
5 1
3 2
2 4
2 5
3 6
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


// El nodo inicial y final sera el nodo 0
public class NCiclosHamiltonianos {

    public static void main(String[] args){

        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();  //
        List<Integer>[] grafo = new List[nNodos];
        for (int i = 0; i < nNodos; i++) {
            grafo[i]=new ArrayList<>();
        }
        int nAristas = teclado.nextInt(); //
        for (int i = 0; i < nAristas; i++) {
           int nodo1 = teclado.nextInt();
           int nodo2 = teclado.nextInt();
           grafo[nodo1].add(nodo2);
           grafo[nodo2].add(nodo1);
        }
        int[]solucion=new int[nNodos];
        solucion[0]=0;
        int nCaminosHamiltonianos = 0;
        nCaminosHamiltonianos = hallarNCiclosHamiltonianos(grafo,solucion, 1, nCaminosHamiltonianos);
        System.out.println("Numero de caminos hamiltonianos posibles:  "+ nCaminosHamiltonianos);
    }

    private static int hallarNCiclosHamiltonianos(List<Integer>[] grafo, int[] solucion, int pos, int nCaminosHamiltonianos) {
        int ultimoNodoAsignado = solucion[pos-1]; //ultimo nodo que hemos asignado
        List<Integer> adyacentes = new ArrayList<>(grafo[ultimoNodoAsignado]); //Obtenemos la lista de adyacentes
        while(!adyacentes.isEmpty()){
            int nodoNuevo = adyacentes.remove(0);
            if(noAsignado(solucion,nodoNuevo,pos-1)){
                solucion[pos]=nodoNuevo;
                if(pos == solucion.length-1  &&  grafo[nodoNuevo].contains(solucion[0])){
                //Si hemos llenado el array solucion y el ultimo nodo conecta con el primer nodo asignado
                    //entonces hemos encontrado un camino hamiltoniano
                        nCaminosHamiltonianos++;
                        System.out.println(Arrays.toString(solucion));
                } else {
                    nCaminosHamiltonianos = hallarNCiclosHamiltonianos(grafo,solucion,pos+1,nCaminosHamiltonianos);
                }
                solucion[pos] = -1;
            }
        }
        return nCaminosHamiltonianos;
    }

    private static boolean noAsignado(int[] solucion, int nodoNuevo, int topeArraySolucion) {
        for (int i = 0; i < topeArraySolucion; i++) {
            if (solucion[i]==nodoNuevo){
                return false;
            }
        }
        return true;
    }
}
