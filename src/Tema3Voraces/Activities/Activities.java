package Tema3Voraces.Activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Activities {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nCasos = teclado.nextInt();
        int[]maximos=new int[nCasos];
        for (int i = 0; i < nCasos; i++) {
            int nActividades = teclado.nextInt();
            List<Activity> actividades = new ArrayList<>(nActividades);
            for (int j=0;j<nActividades;j++){
                int in = teclado.nextInt();
                int fn = teclado.nextInt();
                Activity a = new Activity(in,fn);
                actividades.add(a);
            }
            int[]inicio = new int[nActividades];
            int[]fin = new int[nActividades];
            inicializar(inicio, fin, actividades);
            maxActividades(inicio,fin,maximos,i);
        }
        System.out.println(maximos);
        
    }

    private static void maxActividades(int[] inicio, int[] fin, int[] maximos, int caso) {
        int max;
        max=1;
        int i = 0;
        for(int j=1;j<inicio.length;i++){
            if(fin[i]<=inicio[j]){
                max++;
                i=j;
            }
        }
        maximos[caso]=max;
    }

    private static void inicializar(int[] inicio, int[] fin, List<Activity> actividades) {
        Collections.sort(actividades);
        for(int i=0;i<actividades.size();i=i+2){
            inicio[i]=actividades.get(i).inicio;
            inicio[i]=actividades.get(i+1).inicio;
        }
    }

    private static class Activity implements Comparable<Activity>{
        private int inicio;
        private int fin;

        public Activity(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        @Override
        public int compareTo(Activity activity) {
            if(fin>activity.fin){
                return 1;
            }else if(fin<activity.fin){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
