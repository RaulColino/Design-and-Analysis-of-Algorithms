package Tema4DyV.Median;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.sort;

/* df
6
11 5 6 9 3 7

5
11 5 6 9 3

5
11 5 6 9 6

8
3 6 2 9 3 12 0 2
 */

public class MedianaEnArrayListNoOrdenadoSinOrdenar {

    //metodo 2: utilizar seleccion de mediana con DyV: 0(n)

    //Pregunta: ¿por qué para ciertos valores de n es más
    // lento DyV que ordenar y sacar la mediana si la complejidad de
    // DyV es menor que la de ordenar?

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nEnteros = teclado.nextInt();
        List<Integer> l = new ArrayList<>(nEnteros);
        for (int i = 0; i < nEnteros; i++) {
            l.add(i, teclado.nextInt());
        }
        System.out.println(l);

        mostrarMediana(l);

        sort(l);
        System.out.println(l);
    }

    private static void mostrarMediana(List<Integer> l) {
        int listSize = l.size();
        int mid = listSize / 2;  //k -> pos del elemento a obtener cuando la lista esta ordenada. k=mid obtiene la mediana. k=0 obtiene el menor valor
        if (listSize % 2 == 0) { //par
            int medianaIzq = hallarMediana(l, mid - 1);
            int medianaDer = hallarMediana(l, mid);
            double mediana = (double) (medianaIzq + medianaDer) / 2;
            System.out.println("mediana: " + mediana);
            System.out.println("posiciones: " + l.indexOf(medianaIzq) + ", " + l.lastIndexOf(medianaDer));
        } else { //impar
            int mediana = hallarMediana(l, mid);
            System.out.println("mediana: " + mediana);
            System.out.println("pos del primer valor mediana: " + l.indexOf(mediana));
        }

    }

    //devuelve la posicion de la mediana en el arraylist
    private static int hallarMediana(List<Integer> l, int mid) { //mid = k elemento del array ordenado
        int posPivote = mid / 2;
        int pivote = l.get(posPivote);

        ArrayList<Integer> inf = new ArrayList<>(); //lista de elementos superiores al pivote
        ArrayList<Integer> equiv = new ArrayList<>(); //lista de elementos equivalentes al pivote
        ArrayList<Integer> sup = new ArrayList<>(); //lista de elementos inferiores al pivote
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) < pivote) {
                inf.add(l.get(i));
            } else if (l.get(i) == pivote) {
                equiv.add(l.get(i));
            } else {//(l.get(i)>pivote)
                sup.add(l.get(i));
            }
        }

        if (mid < inf.size()) { //mayoria elementos a la izq (pivote mayor que mediana)
            return hallarMediana(inf, mid); //mediana esta en la izq
        } else if (mid < inf.size() + equiv.size()) {
            return pivote; //hemos encontrado la mediana
        } else {//mayoria elementos a la der (pivote menor que mediana)
            return hallarMediana(sup, mid - inf.size() - equiv.size());
        }
    }


}
