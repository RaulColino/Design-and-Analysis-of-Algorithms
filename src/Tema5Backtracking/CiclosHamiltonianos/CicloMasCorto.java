package Tema5Backtracking.CiclosHamiltonianos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/* 5 nodos, 7 aristas y nodo inicial 2
5 7 2
1 2 5
1 3 2
2 4 1
3 4 3
3 5 5
4 5 5
1 5 1
 */
// hallar el camino hamiltoniano de coste minimo y el numero de caminos hamiltonianos posibles
// nNodos, nAristas, nodoInicial **En esta implementacion se supone que no hay nodo 0
public class CicloMasCorto {

    public int[] cicloMasCorto;
    public int costeCicloMasCorto;
    public int nCaminosHamiltonianos;

    private void mostrarCicloMasCorto(List<Arista>[] g, int nodoInicial) {
        cicloMasCorto = new int[g.length - 1];
        costeCicloMasCorto = Integer.MAX_VALUE;
        nCaminosHamiltonianos = 0;
        int[] solTemp = new int[g.length - 1];
        solTemp[0] = nodoInicial;
        int costeTemp = 0;
        hallarCicloMasCorto(g, solTemp, 1, costeTemp);
        System.out.println("nCaminos: " + nCaminosHamiltonianos);
        System.out.println("coste minimo: " + costeCicloMasCorto);
        System.out.println(Arrays.toString(cicloMasCorto));
    }

    private void hallarCicloMasCorto(List<Arista>[] g, int[] solTemp, int sigPosSol, int costeTemp) {
        int ultimoNodoAsignado = solTemp[sigPosSol - 1];
        ArrayList<Arista> adyacentes = new ArrayList<>(g[ultimoNodoAsignado]);
        while (!adyacentes.isEmpty()) {
            Arista aNueva = adyacentes.remove(0);
            int nodoNuevo = aNueva.n2;
            if (noAsignado(nodoNuevo, solTemp, sigPosSol)) {
                solTemp[sigPosSol] = nodoNuevo;
                costeTemp += aNueva.peso;
                if (sigPosSol == solTemp.length - 1) { //si hemos recorrido todos los nodos comprobamos si el ultimo nodo es adyacente al inicial
                    Arista aAdyNodoInicio = hallarAdyNodoInicio(g, nodoNuevo, solTemp[0]);
                    if (aAdyNodoInicio != null) { //si el ultimo nodo tiene una arista adyacente al inicial hemos encontrado un camino hamiltoniano
                        costeTemp += aAdyNodoInicio.peso; //sumamos el ultimo coste para llegar al nodo inicial
                        nCaminosHamiltonianos++; //y sumamos el nuevo ciclo hamiltoniano encontrado
                        System.out.println(Arrays.toString(solTemp));
                        if (costeTemp < costeCicloMasCorto) { //comprobamos si el ciclo es mejor que el anterior
                            costeCicloMasCorto = costeTemp;
                            cicloMasCorto = Arrays.copyOf(solTemp,solTemp.length); //tiene que asignarse una copia para que funcione
                        }
                        costeTemp -= aAdyNodoInicio.peso;
                    }
                } else {
                    hallarCicloMasCorto(g, solTemp, sigPosSol + 1, costeTemp);
                }
                costeTemp -= aNueva.peso;
                solTemp[sigPosSol] = 0;
            }
        }
    }

    private Arista hallarAdyNodoInicio(List<Arista>[] g, int nodoNuevo, int nodoInicial) {
        for (Arista a : g[nodoNuevo]) {
            if (a.n2 == nodoInicial) {
                return a;
            }
        }
        return null;
    }

    private boolean noAsignado(int nodoNuevo, int[] solTemp, int sigPosSol) {
        for (int i = 0; i < sigPosSol; i++) {
            if (solTemp[i] == nodoNuevo) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nNodos = teclado.nextInt();
        int nAristas = teclado.nextInt();
        int nodoInicial = teclado.nextInt(); //nodo desde el cual empieza el recorrido

        List<Arista>[] g = new List[nNodos + 1];
        for (int i = 1; i < nNodos + 1; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < nAristas; i++) {
            int n1 = teclado.nextInt();
            int n2 = teclado.nextInt();
            int peso = teclado.nextInt();
            Arista a12 = new Arista(n1, n2, peso);
            Arista a21 = new Arista(n2, n1, peso);
            g[n1].add(a12);
            g[n2].add(a21);
        }

        CicloMasCorto c = new CicloMasCorto();
        c.mostrarCicloMasCorto(g, nodoInicial);
    }

    private static class Arista {
        int n1, n2, peso;

        public Arista(int n1, int n2, int peso) {
            this.n1 = n1;
            this.n2 = n2;
            this.peso = peso;
        }
    }
}
