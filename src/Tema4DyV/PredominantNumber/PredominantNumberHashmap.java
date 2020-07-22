package Tema4DyV.PredominantNumber;

import java.util.HashMap;
import java.util.Scanner;
/*
4
1 2 3 3

5
1 2 1 1 3
 */

public class PredominantNumberHashmap { //solo para implementaciones con enteros
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nEnteros = teclado.nextInt();
        int[] v = new int[nEnteros];
        for (int i = 0; i < nEnteros; i++) {
            v[i] = teclado.nextInt();
        }
        hayPredominante(v);
    }

    private static void hayPredominante(int[] v) {
        HashMap<Integer, Integer> map = new HashMap<>(); //Almacena la frecuencia del numero de la posicion
        for (int i = 0; i < v.length; i++) {
            if (map.containsKey(v[i])) {
                int contador = map.get(v[i]) + 1;
                if (contador > v.length / 2) {
                    System.out.println("Predominante: "+v[i]);
                    return;
                } else {
                    map.put(v[i], contador);
                }
            } else {
                map.put(v[i],1);
            }
        }
        System.out.println("No hay predominante");

    }
}

