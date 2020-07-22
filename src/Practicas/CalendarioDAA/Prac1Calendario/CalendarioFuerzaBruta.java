package Prac1Calendario;

import java.util.HashSet;
import java.util.Set;

public class CalendarioFuerzaBruta {
    private int numFilas;
    private int numColumnas;
    private boolean nEqPar;
    private boolean solucionEncontrada;

    public void crearCalendarioFB(int n){ // n es el número de equipos diferentes que habrá en el calendario
        if(n<1){
            System.out.println("Error: Introduzca un número de equipos correcto (número mayor que 0)");
        }else{
            numFilas=n;
            HashSet[] vectorConjuntos= new HashSet[numFilas]; //Creamos un vector que contendrá n conjuntos (con los equipos contra los que ha jugado cada equipo)
            inicializarVectorConjuntos(vectorConjuntos);
            int[][] calendario;
            if(esPar(n)){
                nEqPar=true;
                numColumnas= numFilas-1; //nEquipos par: calendario de n filas y n-1 columnas
            }else{
                nEqPar=false;
                numColumnas= numFilas;  //nEquipos impar: calendario de n filas y n columnas
            }
            calendario=new int[numFilas][numColumnas];

            organizarCalendario(calendario, vectorConjuntos);
            imprimirCalendario(calendario);
        }
    }

    private void organizarCalendario(int[][] calendario, Set[] vectorConjuntosFila) {
        for(int j=0;j<numColumnas;j++){ //Organizamos el calendario columna a columna
            Set<Integer> conjuntoColumna= new HashSet<Integer>(); //Creamos un vector que contendrá los equipos asignados en cada columna del calendario
            int[] vAsignados= new int[numFilas]; //Creamos un vector de enteros para saber si hay un valor asignado en una fila o no en la columna j
            inicializarVectorAsignados(vAsignados);
            solucionEncontrada=false;
            organizarColumna(calendario,0, j, vectorConjuntosFila,conjuntoColumna, 0, vAsignados); //k que representa el número de equipos asignados en la columna j
        }
    }



    private void organizarColumna(int[][] c, int fila,int columna, Set[] vecConjuntosFila,Set conjColumna, int k, int[] vecAsignados) {
        if(k==numFilas) {
            solucionEncontrada=true;
        }else{
            int nEq;
            if(nEqPar){ // si el número de equipo es par NO se asignan descansos(-1)
                nEq=0;
            }else{
                nEq=-1; //-1 equivale a semana de descanso
            }
            for(nEq=nEq; nEq<numFilas; nEq++){
                if (!solucionEncontrada) { // Si todavía no se ha encontrado una solución se continúa buscando soluciones
                    if(vecAsignados[fila]==-2){  // Si la casilla de la fila "fila" y columna "columna" no ha sido asignada se le asigna un valor
                        boolean asignacionCorrecta;
                        if((nEq!=-1)) {
                           asignacionCorrecta=((vecAsignados[nEq] == -2) && (nEq!=fila)); //Si el equipo a asignar no está ocupado descansando o jugando con otro y además no se está asignando a sí mismo, se continúa
                        }else{
                            asignacionCorrecta=true;
                        }
                        if((!vecConjuntosFila[fila].contains(nEq))&&(!conjColumna.contains(nEq))&&(asignacionCorrecta)){ //Si al equipo a asignar A no le hemos asignado el equipoB(o un descanso)
                            c[fila][columna]=nEq;                                                  //en su fila del calendario y además no hemos asignado el B(o un descanso) en
                            vecConjuntosFila[fila].add(nEq);                                       //ninguna parte de la columna correspondiente, entonces se le asigna
                            conjColumna.add(nEq);
                            vecAsignados[fila]=nEq;
                            if((nEq!=-1)){ //Si no estamos asignando un descanso, sino que estamos asignando un equipoB a un equipoA
                                c[nEq][columna]=fila; //entonces al equipoA habrá que asignarle el equipoB
                                vecConjuntosFila[nEq].add(fila);
                                conjColumna.add(fila);
                                vecAsignados[nEq]=fila;
                            }
                            organizarColumna(c, fila+1, columna, vecConjuntosFila,conjColumna, k+1, vecAsignados);
                        }

                    }else{ //En el caso de que haya sido asignada la casilla, se continúa con la siguiente fila
                        organizarColumna(c, fila+1, columna, vecConjuntosFila,conjColumna, k+1, vecAsignados);
                    }
                }
            }
            if(!solucionEncontrada){
                int NumAnteriorFila=fila-1; // número de la fila anterior
                int AsignadofilaAnterior=vecAsignados[fila-1]; //Valor asignado a la fila anterior en la columna j

                vecConjuntosFila[NumAnteriorFila].remove(AsignadofilaAnterior); //Eliminamos el anterior valor asignado del conjunto de la fila anterior
                conjColumna.remove(AsignadofilaAnterior);                      // y también lo eliminamos del conjunto de la columna.
                if((AsignadofilaAnterior!=-1)&&(AsignadofilaAnterior!=-2)) { //si el último valor asignado no ha sido un descanso, ésto quiere decir que habrá que eliminar 2 valores de equipos asignados.
                    vecConjuntosFila[AsignadofilaAnterior].remove(NumAnteriorFila);
                    conjColumna.remove(NumAnteriorFila);
                    vecAsignados[AsignadofilaAnterior]=-2;
                }
                vecAsignados[fila-1]=-2;
            }
        }
    }

    private void imprimirCalendario(int[][] c) { // En la lógica los números de los equipos van de 0 a n-1.
        for(int j=0;j<numColumnas;j++){          //Sin embargo en la presentación, por estética se enumerarán de 1 a n
            if(j==0){
                System.out.print("Eq/Semana");
                System.out.print(" S"+(j+1));
            }
            else {
                System.out.print(" S"+(j+1));
            }
        }
        for(int i=0;i<numFilas;i++){
            System.out.println();
            for(int j=0;j<numColumnas;j++){
                if(j==0){
                    if(i<9){
                    System.out.print("Equipo "+(i+1)+" ");
                    }else{
                        System.out.print("Equipo"+(i+1)+" ");
                    }
                    if(c[i][j]==-1){
                        System.out.print(" X ");
                    }else{
                    System.out.print(" e"+(c[i][j]+1));}
                }
                else {
                    if(c[i][j]==-1){
                        System.out.print(" X ");
                    }else{
                        System.out.print(" e"+(c[i][j]+1));}
                }
            }

        }
    }

    private boolean esPar(int n) {
        return (n%2==0);
    }

    private void inicializarVectorConjuntos(Set[] v) {
        for(int i=0;i<numFilas;i++){
            v[i]=new HashSet<Integer>();
        }
    }
    private void inicializarVectorAsignados(int[] v){
        for(int i=0;i<numFilas;i++){
            v[i]=-2; // -2 Significa que la casilla no ha sido asignada todavía, -1 sigifica descanso, 0..n son los números de los equipos asignados
        }
    }
}





/*DAA Práctica 1  Autor: Raúl Colino Singh */