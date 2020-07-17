import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int L = in.nextInt();
        
        ArrayList<String> previousLine = new ArrayList<String>();
        previousLine.add(Integer.toString(R));
        
        for (int i = 1 ; i < L ; i++) {
            ArrayList<String> currentLine = new ArrayList<String>();
            // first line 
            if (previousLine.size() == 1) {
                currentLine.add("1");
                currentLine.add(previousLine.get(0));
            }
            else {
                int count = 1;
                for (int j = 1 ; j < previousLine.size() ; j++) {
                    if (!previousLine.get(j - 1).equals(previousLine.get(j))) {
                        currentLine.add(Integer.toString(count));
                        currentLine.add(previousLine.get(j - 1));
                        count = 1;
                    } 
                    else {
                        count++;
                    }

                    // case end of line
                    if (j == previousLine.size() - 1) {
                        currentLine.add(Integer.toString(count));
                        currentLine.add(previousLine.get(j));
                    }
                }
            }
            previousLine = currentLine;
            System.err.println(String.join(" ", previousLine));
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(String.join(" ", previousLine));
    }
}