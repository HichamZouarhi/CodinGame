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
        int N = in.nextInt();
        
        List<String> branches = new ArrayList<String>();
        Set<String> branchesSet = new HashSet<String>();
        
        for (int i = 0; i < N; i++) {
            String telephone = in.next();
            for (int j = 0; j < telephone.length(); j++){
                branches.add(telephone.substring(0, j+1));
            }
            System.err.println(telephone);
        }
        
        for (String branche : branches) { 
            branchesSet.add(branche);
            // System.err.println(branche);
            
        }
        
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // The number of elements (referencing a number) stored in the structure.
        System.out.println(branchesSet.size());
    }
}