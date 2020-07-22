package Tema3Voraces.CoinChange;

import java.util.ArrayList;

public class CoinChange {
    public static void main(String[] args) {
        int[]cash = {1,2,5,10,20,50,100,200,500};
        cashExchange(350,cash);

    }

    private static void cashExchange(int value, int[] cash) {
        int remainingValue = value;
        ArrayList<Integer> change = new ArrayList<Integer>();
        for(int i=cash.length-1;i>=0;i--){
            while(cash[i]<=remainingValue) {
                change.add(cash[i]);
                remainingValue -= cash[i];
            }
        }
        System.out.println(change);
    }
}
