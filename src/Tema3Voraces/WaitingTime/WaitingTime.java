package Tema3Voraces.WaitingTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/*
4
ana 1.7
pepe 23.5
maria 4.8
jose 3
 */

public class WaitingTime { //cola clientes en un dentista p.ej
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nClientes = teclado.nextInt();
        ArrayList<Cliente> clientes = new ArrayList<>(nClientes);
        for (int i = 0; i < nClientes; i++) {
            String nom = teclado.next();
            double tReq = teclado.nextDouble();
            Cliente p = new Cliente(nom, tReq);
            if (clientes.contains(p)) {
                System.out.println("ya existe");
            } else {
                clientes.add(p);
            }
        }
        mostrarMejorOrden(clientes);

    }

    private static void mostrarMejorOrden(ArrayList<Cliente> clientes) {
        if (!clientes.isEmpty()) {
            ArrayList<Cliente> sol = obtenerListaOrdenada(clientes);
            System.out.println(sol);
        } else {
            System.out.println("no hay clientes");
        }

    }

    private static ArrayList<Cliente> obtenerListaOrdenada(ArrayList<Cliente> clientes) {
        ArrayList<Cliente> sol = new ArrayList<>();
        ArrayList<Cliente> aux = new ArrayList<>(clientes);
        while (!aux.isEmpty()) {
            Cliente seleccionado = aux.get(0);
            for (Cliente cliente : aux) {
                if (cliente.tRequerido < seleccionado.tRequerido) {
                    seleccionado = cliente;
                }
            }
            sol.add(seleccionado);
            aux.remove(seleccionado);
        }
        return sol;
    }

    private static class Cliente {
        String nombre;
        double tRequerido;

        public Cliente(String nombre, double tRequerido) {
            this.nombre = nombre;
            this.tRequerido = tRequerido;
        }

        @Override
        public String toString() {
            return "Cliente{" +
                    "nombre='" + nombre + '\'' +
                    ", tRequerido=" + tRequerido +
                    '}'+'\n';
        }
    }
}
