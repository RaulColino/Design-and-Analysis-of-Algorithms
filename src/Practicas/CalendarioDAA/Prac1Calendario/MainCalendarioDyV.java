package Prac1Calendario;

public class MainCalendarioDyV {
    public static void main(String args[]){

        System.out.println("Calendario por Fuerza bruta:");
        System.out.println();
        /*Calendario por Fuerza bruta*/
        CalendarioFuerzaBruta calendario= new CalendarioFuerzaBruta();
        calendario.crearCalendarioFB(1);

        System.out.println();
        System.out.println();

        System.out.println("Calendario por Divide y vencerás:");
        System.out.println();
        /*Calendario por Divide y vencerás*/
        CalendarioDyV calendarioDyV = new CalendarioDyV();
        calendarioDyV.crearCalendarioDyV(1);
    }
}




/*DAA Práctica 1  Autor: Raúl Colino Singh */