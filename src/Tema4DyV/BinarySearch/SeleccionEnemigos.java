package Tema4DyV.BinarySearch;

import java.util.Scanner;

public class SeleccionEnemigos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nJug = teclado.nextInt();
        int[]jug = new int[nJug];
        for (int i = 0; i < nJug; i++) { //Se supone q te lo dan ordenado
            jug[i]=teclado.nextInt();
        }
        int nCasos = teclado.nextInt();
        int[] posicioneslMenores = new int[nCasos];
        int[] posicioneslMayores = new int[nCasos];
        for(int i=0; i<nCasos; i++){
            int lMin = teclado.nextInt();
            posicioneslMenores[i]=buscarPosicion(lMin, jug);
            int lMax = teclado.nextInt();
            int pos = posicioneslMenores[i];
            while((jug[pos] != lMax)){
                pos++;
            }
            posicioneslMayores[i]=pos;
        }
        for (int i=0;i<posicioneslMenores.length;i++) {
            System.out.print(posicioneslMenores[i]+" ");
            System.out.println(posicioneslMayores[i]);
        }

    }

    private static int buscarPosicion(int lMax, int[] jug) {
       return busquedaBinariaRec(lMax, jug, 0, jug.length - 1);
    }

    private static int busquedaBinariaRec(int elemAbuscar, int[]v, int low, int high) {
        if (low > high) {
            return -1;
        } else {
            int mid = (low + high) / 2;
            if (v[mid] > elemAbuscar) {
                return busquedaBinariaRec(elemAbuscar, v, low, mid-1);
            } else if (v[mid] < elemAbuscar) {
                return busquedaBinariaRec(elemAbuscar, v, mid+1, high);
            } else {/* v[mid]==elemAbuscar */
                return mid;
            }
        }
    }
}
