package Tema4DyV.Mergesort;

import java.util.Arrays;
/*
5
PedroS 30 40
PabloT 19 10
AlbertL 10 30
PabloL 20 50
SantiR 20 90
 */

public class Mergesort {

    public static void main(String[]args) {
        int[] v = {3, 1, 6, 3, 8, 5, 9, 6, 2, 4};
        int[] sorted = mergeSort(v,0,v.length);
        System.out.println(Arrays.toString(v) + ", length=" + v.length);
        System.out.println(Arrays.toString(sorted) + ", length=" + sorted.length);
    }

    private static int[] mergeSort(int[] v, int first, int last){
       if((last-first) < 2){
           return Arrays.copyOfRange(v,first,last);
       }else {
           int mid=(first+last)/2;
           int[]start= mergeSort(v,first,mid);
           int[]end=mergeSort(v,mid,last);
           int[]merged=merge(start,end);
           return merged;
       }
    }

    private static int[] merge(int[] start, int[] end) {
        int i=0;int j=0;int k =0;
        int[]mix=new int[start.length+end.length];
        while(i<start.length && j<end.length){
            if(start[i]<end[j]){
                mix[k]=start[i];
                i++;
            }else{
                mix[k]=end[j] ;
                j++;
            }
            k++;
        }
        while (i<start.length){
            mix[k]=start[i];
            i++;
            k++;
        }
        while (j<end.length){
            mix[k]=end[j];
            j++;
            k++;
        }
        return mix;
    }


}
