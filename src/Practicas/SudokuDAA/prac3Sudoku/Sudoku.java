import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Sudoku {

    //Atributos
    private int[][] tableroInicial;
    private int[][] tableroSolucion;
    private boolean solucionEncontrada;

    private HashSet[][] matrizConjCuad = new HashSet[3][3];
    private HashSet[] vectorConjFila = new HashSet[9];
    private HashSet[] vectorConjCol = new HashSet[9];


    //Constructores
    public Sudoku(){
        tableroInicial= new int[9][9];  //9 filas y 9 columnas
        tableroSolucion= new int[9][9];
        inicializarConjuntos();
    }

    //Métodos
    public void inicializar(String ruta_nombreFichero){
        System.out.println();
        try{
            vaciarConjuntos();
            FileReader ficheroIn = new FileReader(ruta_nombreFichero);
            BufferedReader bufferIn = new BufferedReader(ficheroIn);
            String linea;
            int fila=0;
            while((linea=bufferIn.readLine()) != null){ //Para cada fila se lee una cadena de números
                int columna=0;
                for(int i=0; i<linea.length();i++){ //Asignamos cada número de la cadena en cada columna
                    //String s = String.valueOf(linea.charAt(i));
                    String s = String.valueOf(linea.charAt(i));
                    if(!s.equals(" ")){
                        int cifra = Integer.parseInt(s);
                        tableroInicial[fila][columna]=cifra;
                        tableroSolucion[fila][columna]=cifra;
                        agregarValorConjuntos(fila,columna,cifra);
                        columna++;
                    }
                }
                fila++; //Pasamos a la siguiente fila
            }
            bufferIn.close();
            System.out.println("Los datos iniciales del sudoku han sido cargados con éxito: ");
            System.out.println();
            mostrarTableroInic();
        } catch (FileNotFoundException e) {
            System.out.println("Error en la búsqueda del fichero de datos:");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al cargar el fichero:");
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error de lectura: Por favor verifique que el fichero contenga 9 columnas y 9 filas");
        } catch (NumberFormatException e){
            System.out.println("Error de lectura (Por favor verifique que el fichero contenga solo números):");
            System.out.println(e.getMessage());
        }
    }

    private void mostrarTableroInic() {
        for(int fila=0;fila<9;fila++){
            if(fila==3 || fila==6){
                for(int col=0;col<20;col++){
                    System.out.print("- ");
                }
                System.out.println();
            }
            for(int col=0;col<9;col++){
                if(col==3 || col==6){
                    System.out.print("|  ");
                }
                if(tableroInicial[fila][col]==0){
                    System.out.print("[]  ");
                }else{
                    System.out.print(tableroInicial[fila][col]);
                    System.out.print("   ");
                }
            }
            System.out.println();
        }

    }

    private void mostrarTableroSol() {
        for(int fila=0;fila<9;fila++){
            if(fila==3 || fila==6){
                for(int col=0;col<20;col++){
                    System.out.print("- ");
                }
                System.out.println();
            }
            for(int col=0;col<9;col++){
                if(col==3 || col==6){
                    System.out.print("| ");
                }
                if(tableroInicial[fila][col]==0){
                    System.out.print("[");
                    System.out.print(tableroSolucion[fila][col]);
                    System.out.print("]");
                }else{
                    System.out.print(" ");
                    System.out.print(tableroSolucion[fila][col]);
                    System.out.print(" ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    private void inicializarConjuntos() {
        for(int fila=0;fila<9;fila++){
            vectorConjFila[fila] = new HashSet();  //Vaciamos los 9 conjuntos de las filas
            vectorConjCol[fila] = new HashSet();  //Vaciamos los 9 conjuntos de las columnas
            for(int columna=0;columna<3;columna++){
                if(fila < 3) {
                    matrizConjCuad[fila][columna] = new HashSet(); //Vaciamos los 9 conjuntos de los cuadrantes
                }
            }
        }
    }

    private void agregarValorConjuntos(int fila, int columna, int cifra) {
        if(cifra<=9&&cifra>=0){
            matrizConjCuad[fila/3][columna/3].add(cifra);  //Añadimos el número al conjunto del cuadrante correspondiente
            vectorConjFila[fila].add(cifra);  //Añadimos el número al conjunto de la fila correspondiente
            vectorConjCol[columna].add(cifra);  //Añadimos el número al conjunto de la columna correspondiente
        }else{
            System.out.println("Se ha encontrado un número mayor que 9 o menor que 0!");
        }
    }


    public void resolver(){
        solucionEncontrada=false;
        resolverPorBacktracking(0,0);
        if(!solucionEncontrada){
            System.out.println();
            System.out.println("El sudoku planteado no tiene solución");
        }
    }

    private void resolverPorBacktracking(int fila, int columna){

        if(tableroSolucion[fila][columna]==0){ //Si no hay un valor fijo (casilla vacía) en la casilla [fila][columna] entonces podemos asignar valores

            int numeroAPoner=1;

            while(!solucionEncontrada && numeroAPoner<10) {//El bucle termina cuando hemos probado con todos los valores y ninguno es válido ó cuando hemos encontrado la solución
                //Si numeroAPoner es un valor que cumple todas las condiciones, entonces se asigna, se actualizan los conjuntos y se continúa en la siguiente posición a asignar
                if(!vectorConjFila[fila].contains(numeroAPoner) && (!vectorConjCol[columna].contains(numeroAPoner) && !matrizConjCuad[fila/3][columna/3].contains(numeroAPoner))) {
                    tableroSolucion[fila][columna]=numeroAPoner; //Se asigna el valor
                    agregarValorConjuntos(fila,columna,numeroAPoner); //Se añade el valor a los correspondientes conjuntos y continúamos...
                    if(fila==8){ //Si hemos llegado a la última fila:
                        if(columna==8){ //Si hemos asignado la última casilla (8,8), hemos encontrado la solución
                            solucionEncontrada=true;
                            System.out.println();
                            System.out.println("La solución del sudoku es la siguiente: ");
                            System.out.println();
                            mostrarTableroSol();
                        }else { //Si hemos llegado a la última fila pero no a la última columna, continuamos en la siguiente columna en la fila 0
                            resolverPorBacktracking(0, columna + 1);
                        }
                    }else{ //Si no hemos llegado a la última fila, seguimos en la siguiente
                        resolverPorBacktracking(fila+1, columna);
                    }
                    eliminarValorConjuntos(fila,columna,numeroAPoner); //Si numeroAPoner no puede ser solución se elimina de los conjuntos correspondientes
                    numeroAPoner++; // y se prueba con la siguiente
                }else{ //Si numeroAPoner no es una cifra válida se prueba con la siguiente
                    numeroAPoner++;
                }

            }
            tableroSolucion[fila][columna]=0;  //Cuando hemos terminado de operar con una casilla que inicialmente estaba vacía la volvemos a dejar en su estado inicial

        }else{ //Si hay un valor fijo asignado en esa casilla se cntinúa en la siguiente (de la siguiente fila)
            if(fila==8){
               if(columna==8){ //Si hemos llegado a la última casilla (8,8) y tiene un elemento fijo, hemos encontrado la solución
                   solucionEncontrada=true;
                   System.out.println();
                   System.out.println("La solución del sudoku es la siguiente: ");
                   System.out.println();
                   mostrarTableroSol();
               }else { //Si hemos llegado a la última fila pero no a la última columna, continuamos en la siguiente columna en la fila 0
                   resolverPorBacktracking(0, columna + 1);
               }
            }else{ //Si no hemos llegado a la última fila, seguimos en la siguiente
                resolverPorBacktracking(fila+1, columna);
            }
        }
    }

    private void eliminarValorConjuntos(int fila, int columna, int cifra) {
        if(cifra<=9&&cifra>=0){
            matrizConjCuad[fila/3][columna/3].remove(cifra);  //Añadimos el número al conjunto del cuadrante correspondiente
            vectorConjFila[fila].remove(cifra);  //Añadimos el número al conjunto de la fila correspondiente
            vectorConjCol[columna].remove(cifra);  //Añadimos el número al conjunto de la columna correspondiente
        }else{
            System.out.println("Se ha encontrado un número mayor que 9 o menor que 0!");
        }
    }

    private void vaciarConjuntos() {
        for(int fila=0;fila<9;fila++){
            vectorConjFila[fila].clear();  //Vaciamos los 9 conjuntos de las filas
            vectorConjCol[fila].clear();  //Vaciamos los 9 conjuntos de las columnas
            for(int columna=0;columna<3;columna++){
                if(fila < 3) {
                    matrizConjCuad[fila][columna].clear(); //Vaciamos los 9 conjuntos de los cuadrantes
                }
            }
        }
    }




}






/*DAA Práctica 3  Autor: Raúl Colino Singh */