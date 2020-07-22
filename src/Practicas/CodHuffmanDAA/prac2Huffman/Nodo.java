public class Nodo  implements Comparable<Nodo>{

    //Atributos
    private Character caracter;
    private int frecuencia;
    private Nodo hijoIzq;
    private Nodo hijoDer;

    //Constructor

    public Nodo(Character caracter, int frecuencia){
        this.caracter=caracter;
        this.frecuencia=frecuencia;
        this.hijoIzq=null;
        this.hijoDer=null;
    }
    public Nodo(Character caracter, int frecuencia, Nodo hijoIzq, Nodo hijoDer){
        this.caracter=caracter;
        this.frecuencia=frecuencia;
        this.hijoIzq=hijoIzq;
        this.hijoDer=hijoDer;
    }

    //Métodos

    public void aumentarFrecuencia(){
        frecuencia++;
    }
    //get/set
    public Character getCaracter() {
        return caracter;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public Nodo getHijoIzq() {
        return hijoIzq;
    }

    public Nodo getHijoDer() {
        return hijoDer;
    }

    @Override
    public int compareTo(Nodo n) {
       if(this.getFrecuencia()< n.getFrecuencia()){
            return -1;
        }else if(this.getFrecuencia()== n.getFrecuencia()){
            return 0;
        }else{
            return 1;
        }
    }

}



/*DAA Práctica 2  Autor: Raúl Colino Singh */
