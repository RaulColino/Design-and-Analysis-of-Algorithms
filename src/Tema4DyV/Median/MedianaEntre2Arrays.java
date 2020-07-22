package Tema4DyV.Median;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
/*
6
-2 3 5 8 12 14
7
-8 -1 3 4 5 11 13

6
-2 3 5 8 12 14
4
-8 3 11 13
 */

public class MedianaEntre2Arrays {

    //se supone que estan ordenados  los dos arraylist

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nEnterosV1 = teclado.nextInt();
        int[] v1 = new int[nEnterosV1];
        for (int i = 0; i < nEnterosV1; i++) {
            v1[i] = teclado.nextInt();
        }
        int nEnterosV2 = teclado.nextInt();
        int[] v2 = new int[nEnterosV2];
        for (int i = 0; i < nEnterosV2; i++) {
            v2[i] = teclado.nextInt();
        }
        double mediana = hallarMediana(v1, v2);
        System.out.println(mediana);

        int[] m = IntStream.concat(IntStream.of(v1), IntStream.of(v2)).toArray();
        Arrays.sort(m);
        System.out.println(Arrays.toString(m));
    }

    private static double hallarMediana(int[] v1, int[] v2) {
        if (v1.length > v2.length)
            return hallarMediana(v2, v1);
        int low = 0;
        int high = v1.length;
        int i = 0, j = 0;
        int mediana = 0;
        while (low <= high) {
            i = (low + high) / 2;
            j = (v1.length + v2.length + 1) / 2 - i;
            if (i < v1.length && j > 0 && v2[j - 1] > v1[i]) {
                low = i + 1;
            } else if (i > 0 && j < v2.length && v2[j] < v1[i - 1]) {
                high = i - 1;
            } else {
                if (i == 0) {
                    mediana = v2[j - 1];
                } else if (j == 0) {
                    mediana = v1[i - 1];
                } else {
                    mediana = Math.max(v1[i - 1], v2[j - 1]);
                }
                break;
            }
        }
        // en el caso de la mediana como primer elemnto central en un array par: return mediana;
        //en este caso vamos a suponer que la mediana en arrays pares es la media de los dos elementos centrales
        if ((v1.length + v2.length) % 2 == 1) { //impar
            return mediana;
        } else { //par
            if (i == v1.length) {
                return (mediana + v2[j]) / 2.0;
            } else if (j == v2.length) {
                return (mediana + v1[i]) / 2.0;
            } else {
                return (mediana + Math.min(v1[i], v2[j])) / 2.0;
            }
        }
    }
}
