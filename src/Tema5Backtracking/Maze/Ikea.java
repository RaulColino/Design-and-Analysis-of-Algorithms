package Tema5Backtracking.Maze;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
6 7 4
2 2 2 2 2 2 2
2 2 1 2 2 0 2
2 6 6 2 3 3 2
2 6 6 5 3 3 2
2 2 5 5 4 2 2
2 2 2 2 4 2 2
*/

public class Ikea {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nFilas = teclado.nextInt();
        int nCols = teclado.nextInt();
        int nSecciones = teclado.nextInt();
        int filaEntrada = 0, colEntrada = 0; //solo necesitamos saber la posicion de la entrada porque el algoritmo distingue la salida no por una posicion dada sino al encontrarse con un 1
        int[][] laberinto = new int[nFilas][nCols];
        for (int i = 0; i < nFilas; i++) {
            for (int j = 0; j < nCols; j++) {
                laberinto[i][j] = teclado.nextInt();
                if (laberinto[i][j] == 0) {
                    filaEntrada = i;
                    colEntrada = j;
                }
            }
        }
        mostrarRecorridoMinimo(laberinto, filaEntrada, colEntrada, nFilas, nCols, nSecciones);
    }

    private static void mostrarRecorridoMinimo(int[][] laberinto, int filaEntrada, int colEntrada, int nFilas, int nCols, int nSecciones) {
        Set<Integer> secVisitadas = new HashSet<>();
        int resultado = resolverLaberinto(laberinto, 0, 0, filaEntrada, colEntrada, nFilas, nCols, nSecciones, secVisitadas);
        System.out.println(resultado);
    }

    private static int resolverLaberinto(int[][] laberinto, int contador, int solActual, int i, int j, int nFilas, int nCols, int nSecciones, Set<Integer> secVisitadas) {
        //si estamos sobre una pared o sobre zona visitada o no merece la pena continuar devolvemos -1
        if (laberinto[i][j] == 2 || laberinto[i][j] == -1 || contador + 1 == solActual) {
            return -1;
        } else {
            //si hemos llegado a la salida y hemos visitado todas las secciones
            if (laberinto[i][j] == 1 && secVisitadas.size() >= nSecciones+1) { //n secciones visitadas en el conjunto = numero de secciones dadas por el usuario + la seccion 0
                solActual = contador;
                return solActual;
            }
            // en caso contrario visitamos la casilla y avanzamos
            contador++; //aumentamos la distancia recorrida
            int casillaAnterior = laberinto[i][j]; //primero guardamos el estado anterior de la casilla, es decir, su numero de seccion
            laberinto[i][j] = -1; //marcamos como visitado
            boolean primeraVezQueSeVisitaLaSeccion = false;
            if (!secVisitadas.contains(casillaAnterior)){ //Hay que tener en cuenta que la entrada 0 es la seccion 0
                secVisitadas.add(casillaAnterior); //añadimos al conjunto la seccion visitada
                primeraVezQueSeVisitaLaSeccion = true;
            }
            //der
            if (j + 1 <= nCols-1) {
                int resultado = resolverLaberinto(laberinto, contador, solActual, i, j + 1, nFilas, nCols, nSecciones, secVisitadas);
                if (resultado != -1) {
                    solActual = resultado;
                }
            }
            //abj
            if (i + 1 <= nFilas-1) {
                int resultado = resolverLaberinto(laberinto, contador, solActual, i + 1, j, nFilas, nCols, nSecciones, secVisitadas);
                if (resultado != -1) {
                    solActual = resultado;
                }

            }
            //izq
            if (j - 1 >= 0) {
                int resultado = resolverLaberinto(laberinto, contador, solActual, i, j - 1, nFilas, nCols, nSecciones, secVisitadas);
                if (resultado != -1) {
                    solActual = resultado;
                }

            }
            //arr
            if (i - 1 >= 0) {
                int resultado = resolverLaberinto(laberinto, contador, solActual, i - 1, j, nFilas, nCols, nSecciones, secVisitadas);
                if (resultado != -1) {
                    solActual = resultado;
                }

            }
            laberinto[i][j] = casillaAnterior;
            if(primeraVezQueSeVisitaLaSeccion){
                secVisitadas.remove(casillaAnterior);
            }
            return solActual;
        }
    }
}
