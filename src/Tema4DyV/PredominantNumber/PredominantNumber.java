package Tema4DyV.PredominantNumber;

import java.util.Arrays;
import java.util.Scanner;
/*
4
1 2 3 3

5
1 1 2 1 0

5
1 1 2 2 0
 */

public class PredominantNumber { //esta implementacion permite trabajar con int,char,String,Object,etc.
    //si mas de la mitad de las veces aparece el mismo numero, éste es predominante

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nEnteros = teclado.nextInt();
        int[] v = new int[nEnteros];
        for (int i = 0; i < nEnteros; i++) {
            v[i] = teclado.nextInt();
        }

        boolean existe = buscarPredominante(v);
        if (!existe) {
            System.out.println("No hay predominante");
        }
    }

    private static boolean buscarPredominante(int[] v) {
        int[] aux = Arrays.copyOf(v, v.length);
        if (hayPrometedor(aux, v.length)) {
            int prometedor = aux[0];
            int c=0;
            for (int num: v) {
                if (num == prometedor){
                    c++;
                }
            }
            if (c > v.length/2){
                System.out.println("Predominante: "+prometedor);
                return true;
            }
        }
        return false;
    }


    private static boolean hayPrometedor(int[] aux, int longitud) {
        if (longitud == 0) {
            return false;

        } else if (longitud == 1) {
            return true;

        } else {
            if (longitud % 2 == 0) {
                int k = 0;
                for (int i = 0; i < longitud; i += 2) {
                    if (aux[i] == aux[i + 1]) {
                        aux[k] = aux[i];
                        k++;
                    }
                }
                return hayPrometedor(aux, k);

            } else { //impar
                if (!hayPrometedor(aux,longitud-1)){
                    aux[0] = aux[longitud-1];
                }
                return true;
            }
        }
    }

}

