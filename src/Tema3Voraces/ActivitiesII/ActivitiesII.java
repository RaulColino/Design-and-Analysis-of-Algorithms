package Tema3Voraces.ActivitiesII;
/*
3
2
1 3 0 3 6 1
3
1 3 1 3 6 1 4 6 1
7
1 3 1 3 4 2 2 7 1 4 8 0 5 8 2 8 10 2 12 19 1
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Collections.sort;

public class ActivitiesII {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int nCasos = teclado.nextInt();
        int[] sol = new int[nCasos];
        for(int i=0;i<nCasos;i++){
            int nActividades = teclado.nextInt();
            ArrayList<Activity> activities = new ArrayList<>(nActividades);
            for(int j=0;j<nActividades;j++){
                int ini = teclado.nextInt();
                int fin = teclado.nextInt();
                int descanso = teclado.nextInt();
                Activity a = new Activity(ini,fin+descanso);
                activities.add(a);
            }
            sol[i] = maxActivities(activities);
        }
        System.out.println(Arrays.toString(sol));

    }

    private static int maxActivities(ArrayList<Activity> activities) {
        sort(activities);
        int max = 1;
        int fin = activities.get(0).fin;
        for(int i=1;i<activities.size();i++){
            if(fin <= activities.get(i).inicio){
                max++;
                fin = activities.get(i).fin;
            }
        }
        return max;
    }

    private static class Activity implements Comparable<Activity> {

        private int inicio;
        private int fin;

        public Activity(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        @Override
        public int compareTo(Activity a) {
            if(fin < a.fin){
                return -1;
            }else if(fin > a.fin){
                return 1;
            }else{
                return 0;
            }
        }
    }
}


