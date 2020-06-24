import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
import java.util.*;

class Graph {
    int numberOfPoints;
    int minX, minY, maxX, maxY, sizeX, sizeY;

    ArrayList<Integer> X = new ArrayList<Integer>();
    ArrayList<Integer> Y = new ArrayList<Integer>();

    private int[] findBoundary(ArrayList<Integer> axis) {
        int minBoundary, maxBoundary;
        if (!axis.isEmpty()) {
            minBoundary = axis.get(axis.indexOf(Collections.min(axis)));
            maxBoundary = axis.get(axis.indexOf(Collections.max(axis)));
        } else {
            minBoundary = 0;
            maxBoundary = 0;
        }
        return new int[] { minBoundary, maxBoundary };

    }

    public void findBoundaries() {
        int[] boundariesX = this.findBoundary(X);
        int[] boundariesY = this.findBoundary(Y);
        minX = boundariesX[0] < 0 ? boundariesX[0] : 0;
        maxX = boundariesX[1] > 0 ? boundariesX[1] : 0;
        minY = boundariesY[0] < 0 ? boundariesY[0] : 0;
        maxY = boundariesY[1] > 0 ? boundariesY[1] : 0;
        minX--;
        minY--;
        maxX++;
        maxY++;
        sizeX = Math.abs(maxX) + Math.abs(minX) + 1;
        sizeY = Math.abs(maxY) + Math.abs(minY) + 1;
    }

    private void processPoint(int i, int j) {
        int indexOfJ = Y.indexOf(j);
        if (X.get(indexOfJ) == i && Y.get(indexOfJ) == j) {
            System.out.print("*");
            X.remove(indexOfJ);
            Y.remove(indexOfJ);
        }
        else {
            this.processVoid(i, j);
        }
    }

    private void processVoid(int i, int j) {
        if (i == 0 && j == 0) {
            System.out.print("+");
        } else if (i == 0 && j != 0) {
            System.out.print("|");
        } else if (i != 0 && j == 0) {
            System.out.print("-");
        } else {
            System.out.print(".");
        }
    }

    public void drawGraph(){
        for(int j = maxY; j >= minY ; j--){
            for (int i = minX ; i <= maxX ; i++){
                if (X.contains(i) && Y.contains(j)){
                    this.processPoint(i, j);
                }
                else {
                    this.processVoid(i, j);
                }
            }
            System.out.println("");
        }
    }

    public void dumpInputs() {
        System.err.println("NumberOfPoints : " + numberOfPoints);
        System.err.println("X : " + X.toString());
        System.err.println("Y : " + Y.toString());
    }

    public void dumpBoundaries() {
        System.err.println("X : size = " + sizeX + " minX = " + minX + " maxX = " + maxX);
        System.err.println("Y : size = " + sizeY + " minY = " + minY + " maxY = " + maxY);
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Graph graph = new Graph();

        int N = in.nextInt();
        graph.numberOfPoints = N;

        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            graph.X.add(x);
            graph.Y.add(y);
        }
        graph.dumpInputs();
        graph.findBoundaries();
        graph.dumpBoundaries();
        graph.drawGraph();
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
