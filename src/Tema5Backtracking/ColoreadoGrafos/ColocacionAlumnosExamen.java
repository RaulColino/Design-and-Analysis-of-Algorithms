package Tema5Backtracking.ColoreadoGrafos;

/*
5 4 2
0 1
0 2
2 3
2 4

5 6 2
0 1
0 2
0 3
0 4
2 3
3 4
*/


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColocacionAlumnosExamen {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();//
        List<Integer>[] grafo = new List[nNodos];
        for (int i = 0; i < nNodos; i++) {
            grafo[i]= new ArrayList<>();
        }
        int nAristas = teclado.nextInt();//
        int nModelos = teclado.nextInt();//
        for (int i = 0; i < nAristas; i++) {
            int nodo1 = teclado.nextInt();
            int nodo2 = teclado.nextInt();
            grafo[nodo1].add(nodo2);
            grafo[nodo2].add(nodo1);
        }
        mostrarHaySuficientesModelos(grafo,nNodos,nModelos);

    }

    private static void mostrarHaySuficientesModelos(List<Integer>[] grafo, int nNodos, int nModelos) {
        int[] sol = new int[nNodos];
        boolean suficientes = tieneSolColoreadoGrafos(grafo,0,nModelos,sol);
        if(suficientes){
            System.out.println("OK");
        }else{
            System.out.println("No hay suficientes");
        }
    }

    private static boolean tieneSolColoreadoGrafos(List<Integer>[] grafo,int pos,int nColores, int[] sol) {
        sol[pos]=0;
        for (int color = 1; color <= nColores; color++) {
           sol[pos] = color;
           if(esColorValido(grafo,pos,sol,color)){
               if(pos == sol.length-1){
                   printSol(sol);
                   return true;
               }else{
                   boolean solEncontrada=tieneSolColoreadoGrafos(grafo,pos+1,nColores,sol);
                   if(solEncontrada){
                       return true;
                   }
               }
           }
        }
        return false;
    }

    private static void printSol(int[] sol) {
        for (int i = 0; i < sol.length; i++) {
            System.out.print("nodo"+i+":"+sol[i]+" ");
        }
        System.out.println();
    }

    private static boolean esColorValido(List<Integer>[] grafo, int pos, int[] sol,int color) {
        for (int adj: grafo[pos]) {
            if(sol[adj]==color){
                return false;
            }
        }
        return true;
    }
}
