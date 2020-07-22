package Tema4DyV.Mergesort;

import java.util.Arrays;
import java.util.Scanner;

/*
10
3 1 6 3 8 5 9 6 2 4

7
27 0 3 45 8 1 4

*/

public class MergesortMio {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nEnteros = teclado.nextInt();
        int[] v = new int[nEnteros];
        for (int i = 0; i < nEnteros; i++) {
            v[i]=teclado.nextInt();
        }
        System.out.println(Arrays.toString(v));
        mergeSort(v);
        System.out.println(Arrays.toString(v));

    }

    private static void mergeSort(int[] v) {
        if (v.length < 2) { // si el array es de tamaño 1
            return;
        } else { // si el array es de tamaño mayor que 1
            int mid = v.length / 2;

            int[] vLow = new int[v.length / 2];
            int[] vHigh = new int[v.length - vLow.length];
            for (int i = 0; i < vLow.length; i++) {
                vLow[i]=v[i];
            }
            for (int i = vLow.length; i < v.length; i++) {
                vHigh[i-vLow.length]= v[i];
            }

            mergeSort(vLow);
            mergeSort(vHigh);
            merge(vLow,vHigh,v);
        }
    }

    private static void merge(int[] vLow, int[] vHigh, int[] v) {
        int i=0;
        int j=0;
        int k=0;
        while(i<vLow.length && j<vHigh.length){
            if (vLow[i]<vHigh[j]){
                v[k] = vLow[i];
                i++;
            }else{
                v[k]=vHigh[j];
                j++;
            }
            k++;
        }
        while (i<vLow.length){
            v[k]=vLow[i];
            i++;
            k++;
        }
        while(j<vHigh.length){
            v[k]=vHigh[j];
            j++;
            k++;
        }
    }
}
