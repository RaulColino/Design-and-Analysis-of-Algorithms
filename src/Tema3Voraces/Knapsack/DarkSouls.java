package Tema3Voraces.Knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DarkSouls {

    public static class Data {
        double[] profit;
        double[] weight;
        double maxWeight;
    }

    private static int getBestItem(Data data, List<Integer> cand) {
        double bestRatio = 0;
        int bestItem = 0;
        for (int i = 0; i < cand.size(); i++) {
            int c = cand.get(i);
            double r = data.profit[c] / data.weight[c];
            if (r > bestRatio) {
                bestRatio = r;
                bestItem = i;
            }
        }
        return bestItem;
    }

    private static boolean isFeasible(Data data, int bestItem, double freeWeight){
        return (freeWeight - data.weight[bestItem] > 0);
    }

    public static double[] greedyAlgorithmKS(Data data) {
        int n = data.profit.length;
        ArrayList<Integer> candidates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }
        double freeWeight = data.maxWeight;
        double[] sol = new double[n];
        boolean isSol = false;
        while (!isSol && !candidates.isEmpty()) {
            int bestItemIdx = getBestItem(data, candidates);
            int bestItem = candidates.remove(bestItemIdx);
            if (isFeasible(data, bestItem, freeWeight)) {
                sol[bestItem] = 1.0;
                freeWeight -= data.weight[bestItem];
            } else{
                //Split the last item, if possible
                sol[bestItem] = freeWeight/data.weight[bestItem];
                isSol = true;
            }
        }
        return sol;
    }

    public static void printSol(Data data, double[] sol) {
        System.out.println("item" +"\t" + "profit"  +"\t" +   "weight"  +"\t" +   "Ratio"  +"\t" +  "%Item");
        int n = data.profit.length;
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t\t" + data.profit[i] + "\t"
                    + data.weight[i] + "\t" + String.format("%.2f",data.profit[i] / data.weight[i])
                    + "\t" + String.format("%.2f",sol[i]));
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random(1234);
        int n = 10;
        Data data = new Data();
        data.profit = new double[n];
        data.weight = new double[n];
        data.maxWeight = 0;

        for (int i = 0; i < n; i++) {
            data.profit[i] = Math.round(rnd.nextDouble() * 96 + 44);
            data.weight[i] = Math.round(rnd.nextDouble() * 89 + 15);
            data.maxWeight += data.weight[i];
        }
        data.maxWeight *= 0.60;


        double[] sol = greedyAlgorithmKS(data);
        printSol(data, sol);
    }
}
