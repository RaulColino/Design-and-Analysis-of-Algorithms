package Tema5Backtracking.CiclosHamiltonianos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Parcial 2  => Problema del viaje de thanos
public class viajeThanos {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        List<Integer>[] grafo = new List[nNodos];
        for (int i = 0; i < nNodos; i++) {
            grafo[i]=new ArrayList<>();
        }
        int nAristas = teclado.nextInt();
        for (int i = 0; i < nAristas; i++) {
            int nodo1 = teclado.nextInt();
            int nodo2 = teclado.nextInt();
            grafo[nodo1].add(nodo2);
            grafo[nodo2].add(nodo1);
        }
        mostrarNumeroDeCaminosHamiltonianos(grafo,nNodos);
    }

    private static void mostrarNumeroDeCaminosHamiltonianos(List<Integer>[] grafo, int nNodos) {
        int[] sol = new int[nNodos];
        sol[0]=0;
        int nCaminos=0;
        nCaminos = hallarHamiltonianos(grafo,sol,1,nCaminos);
        System.out.println(nCaminos);
    }

    private static int hallarHamiltonianos(List<Integer>[] grafo, int[] sol, int pos, int nCaminos) {
        int ultimoNodoAsignado = sol[pos-1];
        List<Integer> ady = new ArrayList<>(grafo[ultimoNodoAsignado]);
        while(!ady.isEmpty()){
           int nodo = ady.remove(0);
           if(!asignado(nodo,sol,pos-1)){
               sol[pos]=nodo;
               if(pos == sol.length-1  &&  grafo[nodo].contains(sol[0])){
                   nCaminos++;
                   printSol(sol);
               }else{
                   nCaminos = hallarHamiltonianos(grafo,sol,pos+1,nCaminos);
               }
           }
       }
       return nCaminos;
    }

    private static void printSol(int[] sol) {
        for (int i = 0; i < sol.length; i++) {
            System.out.print(sol[i]+" ");
        }
        System.out.println();
    }

    private static boolean asignado(int nodo, int[] sol, int limite) { //Devuelve true si ya ha sido asignado a la solucion
        int i =0;
        while(i<=limite){
            if(sol[i]==nodo){
                return true;
            }else{
                i++;
            }
        }
        return false;
    }
}
