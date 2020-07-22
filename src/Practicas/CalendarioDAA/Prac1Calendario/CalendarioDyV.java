package Prac1Calendario;

public class CalendarioDyV {
    int[][] calendario;

    public void crearCalendarioDyV(int nEq) {
        if (nEq<1){
            System.out.println("Error: Introduzca un número de equipos correcto (número mayor que 0)");
        } else {
            if (esPar(nEq)) { // nEq es PAR
                calendario = new int[nEq][nEq - 1];  //Ej: nEq=6, int[6][5]
                organizarCalendario(nEq, calendario);
                imprimirCalendario(nEq, nEq - 1, calendario);
            } else { // nEq es IMPAR
                calendario = new int[nEq + 1][nEq]; //Ej: nEq=3, int[4][3]
                organizarCalendario(nEq, calendario);
                imprimirCalendario(nEq, nEq, calendario);
            }
        }


    }

    private void organizarCalendario(int nEquipos, int[][] tabla) {
        if (nEquipos == 2) { //Caso base
            tabla[0][0] = 1;
            tabla[1][0] = 0;
        } else if (!esPar(nEquipos)) {  //nEquipos es IMPAR //Ej: nEquipos=3
            organizarCalendario(nEquipos + 1, calendario); //llamada recusiva con nEquipos+1 Ej: nEquipos=3+1=4
            for (int fila = 0; fila < nEquipos; fila++) {   //Ej: Desde fila 0 hasta fila 2 (nEquipos=3)
                for (int columna = 0; columna < nEquipos; columna++) { //Ej: Desde columna 0 hasta columna 2 (nEquipos=3)
                    if (tabla[fila][columna] == nEquipos) { //Si la casilla [fila][columna] es igual a nEquipos
                        tabla[fila][columna] = -1; //Cambiamos nEquipos por descansos Ej: cambiar 3 por -1
                    }
                }
            }
        } else { //nEquipos es PAR
            int m = nEquipos / 2; //Ej: nEquipos=6,  m=3
            organizarCalendario(m, tabla);            //organizamos el cuadrante superior izquierdo

            if (esPar(m)) { // si m es PAR, es decir nEquipos es potencia de 2
                for (int fila = m; fila < nEquipos; fila++) { //organizamos el cuadrante inferior izquierdo
                    for (int columna = 0; columna < m - 1; columna++) {
                        tabla[fila][columna] = tabla[fila - m][columna] + m;
                    }
                }
                for (int fila = 0; fila < m; fila++) {  //organizamos el cuadrante superior derecho
                    for (int columna = m - 1; columna < nEquipos - 1; columna++) {
                        if (fila + columna <= nEquipos - 2) {//Ej: nEquipos=4, casilla [1][2] no cumple la condición
                            tabla[fila][columna] = fila + columna + 1;
                        } else {
                            tabla[fila][columna] = fila + columna + 1 - m;
                        }
                    }
                }
                for (int fila = m; fila < nEquipos; fila++) { //organizamos el cuadrante inferior derecho
                    for (int columna = m - 1; columna < nEquipos - 1; columna++) {
                        if (fila > columna) { //Ej: nEquipos=4, casilla [2][2] no cumple la condición
                            tabla[fila][columna] = fila - columna - 1;
                        } else {
                            tabla[fila][columna] = fila + (m - 1) - columna;
                        }
                    }
                }

            } else { // si m es IMPAR, es decir nEquipos NO es potencia de 2
                for (int fila = m; fila < nEquipos; fila++) { //organizamos el cuadrante inferior izquierdo
                    for (int columna = 0; columna < m; columna++) {
                        if (tabla[fila - m][columna] == -1) { //Ej: nEquipos=6, [3-3=0][2], [4-3=1][1] y [5-3=2][0] == -1
                            tabla[fila][columna] = -1;
                        } else {
                            tabla[fila][columna] = tabla[fila - m][columna] + m;
                        }
                    }
                }
                for (int fila = 0; fila < m; fila++) {  //Sustituimos -1 de los cuadrantes de la izquierda por sus equipos correspondientes
                    for (int columna = 0; columna < m; columna++) {
                        if (tabla[fila][columna] == -1) {
                            tabla[fila][columna] = fila + m;
                            tabla[fila + m][columna] = fila;
                        }
                    }
                }
                for (int fila = 0; fila < m; fila++) {   //organizamos el cuadrante superior derecho
                    for (int columna = m; columna < nEquipos - 1; columna++) {
                        if (fila + columna <= nEquipos - 2) {//Ej: nEquipos=4, casilla [1][2] no cumple la condición
                            tabla[fila][columna] = fila + columna + 1;
                        } else {
                            tabla[fila][columna] = fila + columna + 1 - m;
                        }
                    }
                }
                for (int fila = m; fila < nEquipos; fila++) { //organizamos el cuadrante inferior derecho
                    for (int columna = m; columna < nEquipos - 1; columna++) {
                        if (fila > columna) { //Ej: nEquipos=4, casilla [2][2] no cumple la condición
                            tabla[fila][columna] = fila - columna - 1;
                        } else {
                            tabla[fila][columna] = fila + (m - 1) - columna;
                        }
                    }
                }
            }
        }
    }


    private void imprimirCalendario(int nFilas, int nColumnas, int[][] c) {
        // En la lógica los números de los equipos van de 0 a n-1.
        //Sin embargo en la presentación, por estética se enumerarán de 1 a n
        for (int j = 0; j < nColumnas; j++) {
            if (j == 0) {
                System.out.print("Eq/Semana");
                System.out.print(" S" + (j + 1));
            } else {
                System.out.print(" S" + (j + 1));
            }
        }
        for (int i = 0; i < nFilas; i++) {
            System.out.println();
            for (int j = 0; j < nColumnas; j++) {
                if (j == 0) {
                    if(i<9){
                        System.out.print("Equipo "+(i+1)+" ");
                    }else{
                        System.out.print("Equipo"+(i+1)+" ");
                    }
                    if (c[i][j] == -1) {
                        System.out.print(" X ");
                    } else {
                        System.out.print(" e" + (c[i][j] + 1));
                    }
                } else {
                    if (c[i][j] == -1) {
                        System.out.print(" X ");
                    } else {
                        System.out.print(" e" + (c[i][j] + 1));
                    }
                }
            }

        }
    }


    private boolean esPar(int n) {

        return (n % 2 == 0);
    }

}




/*DAA Práctica 1  Autor: Raúl Colino Singh */