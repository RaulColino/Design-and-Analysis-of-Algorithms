package Tema4DyV.Median;

import java.util.Arrays;
import java.util.Scanner;

/*
10
3 1 6 3 8 5 9 6 2 4

*/


public class MedianaEnArrayNoOrdenado {

    //metodo 1: mergesort y coger el (0+l.length)/2 elem : O(nlogn)

// Vamos a considerar que la mediana sería
// el elemento que ocuparía la posición (n+1)/2 si los elementos
// estuvieran ordenados.


    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nEnteros = teclado.nextInt();
        int[] v = new int[nEnteros];
        for (int i = 0; i < nEnteros; i++) {
            v[i] = teclado.nextInt();
        }
        double mediana = hallarMediana(v);
        System.out.println(mediana);
    }

    private static double hallarMediana(int[] v) {
        int[] vOrdenado = mergesort(v, 0, v.length);
        System.out.println(Arrays.toString(vOrdenado));
        if ((v.length % 2) != 0) {
            int posMediana = v.length / 2;
            return vOrdenado[posMediana];
        } else {
            int centralIzq = (v.length / 2) - 1;
            int centralDer = v.length / 2;
            return (double)(vOrdenado[centralIzq]+vOrdenado[centralDer])/2.0;
        }
    }

    private static int[] mergesort(int[] v, int low, int high) {
        if (high - low < 2) { //si tamaño del array es 2
            return Arrays.copyOfRange(v, low, high);
        } else {// si tamaño del array es mayor que 2 lo dividimos
            int mid = (low + high) / 2;
            int[] vLow = mergesort(v, low, mid);
            int[] vHigh = mergesort(v, mid, high);
            int[] vMerged = merge(vLow, vHigh);
            return vMerged;
        }
    }

    private static int[] merge(int[] vLow, int[] vHigh) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] vMerged = new int[vLow.length + vHigh.length];
        while (i < vLow.length && j < vHigh.length) {
            if (vLow[i] < vHigh[j]) {
                vMerged[k] = vLow[i];
                i++;
            } else {
                vMerged[k] = vHigh[j];
                j++;
            }
            k++;
        }
        while (i < vLow.length) {
            vMerged[k] = vLow[i];
            i++;
            k++;
        }
        while (j < vHigh.length) {
            vMerged[k] = vHigh[j];
            j++;
            k++;
        }
        return vMerged;
    }
}
