import java.io.*;
import java.util.*;

public class CodificHuffman {

    //Atributos
    private HashMap<Character, Integer> indiceDeFrecuencias;
    private PriorityQueue<Nodo> colaPrioridadNodos;
    private String codigoCaracter;

    //Constructor
    public CodificHuffman(){
        indiceDeFrecuencias = new HashMap<>();
        colaPrioridadNodos = new PriorityQueue<>();
    }

    //Métodos
    public void codificarPorHuffman(String DirFicheroDeEntrada, String DirFicheroDeSalida){
        calcularFrecuencias(DirFicheroDeEntrada);  //Aquí se invoca el método que calcula las frecuencias
        construirArbolDeCodificacion();           //Aquí se invoca el método que crea el árbol de codificación
        try {
            FileReader ficheroIn = new FileReader(DirFicheroDeEntrada);
            BufferedReader bufferIn = new BufferedReader(ficheroIn);

            FileWriter ficheroOut = new FileWriter(DirFicheroDeSalida);
            BufferedWriter bufferOut = new BufferedWriter(ficheroOut);

            String lineaEntrada;
            String lineaSalida;
            while((lineaEntrada=bufferIn.readLine()) != null){ //Si la línea leida no es una línea vacía la codificamos y escribimos
                lineaSalida = codificar(lineaEntrada); //Aquí es donde se invoca el método que devuelve la cadena codificada
                bufferOut.write(lineaSalida); //Escribimos la línea codificada
                bufferOut.newLine(); //Realizamos un salto de línea tras escribir la línea codificada
            }

            bufferIn.close();
            bufferOut.close();

            System.out.println();
            System.out.println("Fichero codificado con éxito");

        } catch (FileNotFoundException e) {
            System.out.println("Error de lectura del fichero: Revise la dirección del fichero");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error de lectura/escritura");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    public void calcularFrecuenciasFichero(String direcFichero){
        calcularFrecuencias(direcFichero);
        if(indiceDeFrecuencias.isEmpty())
            System.out.println("ERROR: No se han encontrado caracteres en el fichero.");
        else
            System.out.println("Las frecuencias de los caracteres del fichero '"+direcFichero+"' son:");
            mostrarIndiceDeFrecuencias();
    }

    private void calcularFrecuencias(String dirFichero){ //Método que calcula las frecuencias de cada carácter en un fichero
        indiceDeFrecuencias.clear();   //Inicializamos vacío el HashMap donde vamos a guardar los nodos con sus frecuencias
        try{
            FileReader ficheroIn = new FileReader(dirFichero);
            BufferedReader bufferIn = new BufferedReader(ficheroIn);
            String lineaEntrada;
            while((lineaEntrada=bufferIn.readLine()) != null){
                actualizarFrecuencias(lineaEntrada); //Aquí se invoca el método que actualiza las frecuencias de los caracteres en cada linea
            }
            bufferIn.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    private void mostrarIndiceDeFrecuencias(){
        Iterator it = indiceDeFrecuencias.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry par = (HashMap.Entry) it.next();
            System.out.println("Carácter: '"+par.getKey()+"' valor: "+par.getValue());
        }
    }

    private void actualizarFrecuencias(String cadena) { //Actualiza las frecuencias del indiceDeNodos
        for(int i=0; i<cadena.length();i++){             //Se recorre la cadena carácter a carácter para ver la frecuencia
            Character caracter = cadena.charAt(i);
            if(indiceDeFrecuencias.containsKey(caracter)){    //Si ya existe el nodo se le aumenta la frecuencia
                int frecNueva=0;
                frecNueva=indiceDeFrecuencias.get(caracter)+1;
                indiceDeFrecuencias.put(caracter,frecNueva);
            } else {                                 //En caso contrario se crea un nuevo nodo con frecuencia 1
                indiceDeFrecuencias.put(caracter,1);
            }
        }
    }

    private void construirArbolDeCodificacion(){
        colaPrioridadNodos.clear();  //Inicializamos vacía la cola de prioridad
        Iterator it =  indiceDeFrecuencias.entrySet().iterator();
        while (it.hasNext()){                               //Creamos los nodos con sus frecuencias y los añadimos a la cola de prioridad
            HashMap.Entry pair = (HashMap.Entry)it.next();
            Nodo n = new Nodo((Character) pair.getKey(),(int) pair.getValue());
            colaPrioridadNodos.offer(n);
        }

        while(colaPrioridadNodos.size()>1){ //Fusionamos los nodos/árboles de dos en dos hasta que quede un único árbol
            Nodo n1 = colaPrioridadNodos.poll();
            Nodo n2 = colaPrioridadNodos.poll();
            fusionarNodos(n1,n2);
        }

    }

    private void fusionarNodos(Nodo nodoIzq, Nodo nodoDer) { //nodo1 tiene menor frecuencia que nodo2
        int frecuenciaNueva = nodoIzq.getFrecuencia()+nodoDer.getFrecuencia();
        Nodo nuevo = new Nodo(null,frecuenciaNueva,nodoIzq,nodoDer);//A la izq-> nodos de menos frecuencia, der-> nodos de mayor frecuencia
        colaPrioridadNodos.offer(nuevo); //Añadimos el nuevo nodo a la cola de prioridad
    }


    public void codificarPalabra(String cadena) {
        indiceDeFrecuencias.clear();   //Inicializamos vacío el HashMap donde vamos a guardar los nodos con sus frecuencias
        actualizarFrecuencias(cadena);

        System.out.println();
        System.out.println("Las frecuencias de los caracteres de '"+cadena+"' son:");
        mostrarIndiceDeFrecuencias();

        construirArbolDeCodificacion();

        System.out.println();
        System.out.println("La correspondiente codificación de '"+cadena+"' es:");
        System.out.println(codificar(cadena));
    }

    private String codificar(String cadena) {
        String cadenaCodificada = "";
        for(int i=0; i<cadena.length();i++){             //Se recorre la cadena carácter a carácter para ver la frecuencia
            Character caracter = cadena.charAt(i);
            cadenaCodificada = cadenaCodificada+codigo(caracter)+" "; //Añadimos una separacion entre cada código para poder distinguirlos
        }
        return cadenaCodificada;
    }

    private String codigo(Character caracter) {
        boolean caracterEncontrado = false;
        String codigo="";
        codigoCaracter = null;

        caracterEncontrado = buscarCaracter(colaPrioridadNodos.peek().getHijoIzq(),caracter,codigo+"0");
        if (!caracterEncontrado)
            buscarCaracter(colaPrioridadNodos.peek().getHijoDer(),caracter,codigo+"1");

        if(codigoCaracter==null){
            System.out.println("Error al codificar el carácter: "+caracter);
            return "?";
        }else{
            return codigoCaracter;
        }
    }

    private boolean buscarCaracter(Nodo n,Character c,String cod) {
        if (n != null) {
            boolean encontrado = false;
            if ((n.getCaracter() != null) && (n.getCaracter() == c)) { //Si el nodo n contiene el caracter se avisa que se ha encontrado y asignamos el codigo correspondiente
                codigoCaracter = cod;
                return true;
            } else {
                if (n.getHijoIzq() != null) { //Si no es el nodo que buscamos continuamos buscando en la rama izquierda
                    encontrado = buscarCaracter(n.getHijoIzq(), c, cod + "0");
                    if (encontrado) { //Si se ha encontrado en la rama izq avisamos de que se ha encontrado
                        return true;
                    } else if (n.getHijoDer() != null) { //Si no buscamos en la rama derecha
                        return buscarCaracter(n.getHijoDer(), c, cod + "1");
                    } else { // si no se ha encontrado en ninguna rama devolvemos un false
                        return false;
                    }
                } else {
                    if (n.getHijoDer() != null) { //Si no buscamos en la rama derecha
                        return buscarCaracter(n.getHijoDer(), c, cod + "1");
                    } else { // si no se ha encontrado en ninguna rama devolvemos un false
                        return false;
                    }
                }
            }
        }else{
            return false;
        }
    }
}


/*DAA Práctica 2  Autor: Raúl Colino Singh */
