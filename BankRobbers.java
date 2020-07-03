import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Vault {
    int C, N, time;
}

class Robber {
    int vault, timeSpent;
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int V = in.nextInt();
        System.err.println("Robbers = " + R + " Vaults = " + V);
        ArrayList<Double> timesInVaults = new ArrayList<Double>();
        ArrayList<Double> timeByRobber = new ArrayList<Double>(Collections.nCopies(R, 0.0));
        for (int i = 0; i < V; i++) {
            int C = in.nextInt();
            int N = in.nextInt();
            System.err.println(C + " " + N);
            double timeInThisVault = Math.pow(10, N) * Math.pow(5, (C - N));
            timesInVaults.add(timeInThisVault);
        }
        
        for (int i = 0 ; i < timesInVaults.size() ; i++){
            timeByRobber.set(0 , timeByRobber.get(0) + timesInVaults.get(i));
            Collections.sort(timeByRobber);
        }
       
        System.err.println(timesInVaults);
        double duration = Collections.max(timeByRobber);
        System.out.println((int) duration);

    }
}