package Tema3Voraces.Knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/*
5
PedroS 30 40
PabloT 19 10
AlbertL 10 30
PabloL 20 50
SantiR 20 90
 */

public class Pacto { // No es exactamente knapsack porque no hay limite de peso, pero si de valor. El objetivo es alcanzar ese valor limite con el peso minimo.

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nPoliticos = teclado.nextInt();
        Politico[] politicos = new Politico[nPoliticos];
        int totalDip = 0;
        for (int i = 0; i < nPoliticos; i++) {
            String nom = teclado.next();
            int nDip = teclado.nextInt();
            totalDip += nDip;
            int nPrej = teclado.nextInt();
            Politico p = new Politico(nom, nDip, nPrej);
            politicos[i] = p;
        }
        MostrarPactosGanador(politicos, totalDip);

    }

    private static void MostrarPactosGanador(Politico[] politicos, int totalDip) {
        //devuelve la posicion del politico con mas diputados
        int posGanador = buscarGanador(politicos);
        System.out.println(politicos[posGanador]);

        List<Politico> pactantes = buscarPactantesGreedyKnapsack(politicos, posGanador, totalDip);
        System.out.println(pactantes);

    }

    private static List<Politico> buscarPactantesGreedyKnapsack(Politico[] politicos, int posGanador, int totalDip) {
        List<Politico> candidatos = new ArrayList<>(Arrays.asList(politicos));
        candidatos.remove(politicos[posGanador]);
        List<Politico> elegidos = new ArrayList<>();
        int dipObtenidos = politicos[posGanador].nDip;
        while (!candidatos.isEmpty() && dipObtenidos < totalDip / 2+1) {
            Politico elegido = buscarMejorCandidato(candidatos);
            if (dipObtenidos + elegido.nDip > totalDip / 2+1) { //si nos pasamos de la mitad+1 solo cogemos los diputados necesarios para obtener el minimo de diputados para obtener la mayoria absoluta y segun el enunciado el prejucio sera el num de diputados que cogemos
                elegido.nDip = totalDip/2+1 - dipObtenidos;
                elegido.nPrej = elegido.nDip;
            }
            dipObtenidos += elegido.nDip;
            elegidos.add(elegido);
            candidatos.remove(elegido);
        }
        return elegidos;
    }

    private static Politico buscarMejorCandidato(List<Politico> candidatos) { //busca el de mayor ratio valor/peso
        double mejorRatio = 0;
        Politico elegido = null;
        for (Politico p : candidatos) {
            double ratioP = (double) p.nDip / p.nPrej;
            if (ratioP > mejorRatio) {
                mejorRatio = ratioP;
                elegido = p;
            }
        }
        return elegido;
    }

    private static int buscarGanador(Politico[] politicos) {
        int ganador = 0;
        for (int i = 0; i < politicos.length; i++) {
            if (politicos[i].nDip > politicos[ganador].nDip) {
                ganador = i;
            }
        }
        return ganador;
    }

    private static class Politico {
        String nom;
        int nDip;
        int nPrej;

        public Politico(String nom, int nDip, int nPrej) {
            this.nom = nom;
            this.nDip = nDip;
            this.nPrej = nPrej;
        }

        @Override
        public String toString() {
            return "nombre "+ nom + '\'' +
                    ", nDip=" + nDip +
                    ", nPrej=" + nPrej +
                    '\n';
        }
    }

}
