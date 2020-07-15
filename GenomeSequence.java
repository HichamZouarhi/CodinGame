import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {
    
    public static String mergeSubseqs(String subseq1, String subseq2, int index) {
        return subseq1 + subseq2.substring(index, subseq2.length());
    }

    public static void main(String args[]) {
        ArrayList<String> subseqs = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String subseq = in.next();
            subseqs.add(subseq);
            System.err.println(subseq);
        }
        subseqs.sort(Comparator.comparingInt(String::length));
        int result = 0;
        while (subseqs.size() > 1) {
            System.err.println("Checking " + subseqs.get(0));
            int maxCharMerged = 0;
            int maxCharMergedIndex = 0;
            Boolean mergeAtEndOfSubseq = false;
            Boolean subseqsMatched = false;
            for (int i = 1 ; i < subseqs.size() ; i++) {
                // if a subseqs contains another one
                if (!subseqs.get(i).contains(subseqs.get(0))) {
                    for (int j = subseqs.get(0).length() - 1; j >= 0; j--) {
                        System.err.println("chars checked = " + j);
                        int lastPartIndexI = subseqs.get(i).length() - 1 - j;
                        int lastPartIndex0 = subseqs.get(0).length() - 1 - j;
                        String lastPartI = subseqs.get(i).substring(lastPartIndexI, subseqs.get(i).length());
                        String firstPartI = subseqs.get(i).substring(0, j + 1); 
                        String lastPart0 = subseqs.get(0).substring(lastPartIndex0, subseqs.get(0).length());
                        String firstPart0 = subseqs.get(0).substring(0, j + 1);
                        System.err.println("Comparing " + lastPart0 + " & " + firstPartI);
                        System.err.println("Comparing " + lastPartI + " & " + firstPart0);
                        // case where first part of 1st subseq overlaps with last part of another subseq
                        if (lastPartI.equals(firstPart0)) {
                            System.err.println("subseq overlap at it the end of subseq " + subseqs.get(i));
                            System.err.println( "lastPartI " + lastPartI + " == firstPart0 = " + firstPart0);
                            maxCharMerged = j + 1;
                            maxCharMergedIndex = i;
                            mergeAtEndOfSubseq = true;
                            subseqsMatched = true;
                            break;
                        }
                        else if (firstPartI.equals(lastPart0)) {
                            System.err.println("subseq overlap at it the start of subseq " + subseqs.get(i));
                            System.err.println( "lastPart0 " + lastPart0 + " == firstPartI = " + firstPartI);
                            maxCharMerged = j + 1;
                            maxCharMergedIndex = i;
                            mergeAtEndOfSubseq = false;
                            subseqsMatched = true;
                            break;
                        }
                    }
                }
                else {
                    subseqsMatched = true;
                }
            }

            if (maxCharMerged > 0) {
                if (mergeAtEndOfSubseq) {
                    subseqs.set(maxCharMergedIndex, mergeSubseqs(subseqs.get(maxCharMergedIndex), subseqs.get(0), maxCharMerged));
                }
                else {
                    subseqs.set(maxCharMergedIndex, mergeSubseqs(subseqs.get(0), subseqs.get(maxCharMergedIndex), maxCharMerged));
                }

                System.err.println("subseqs merged = " + subseqs.get(maxCharMergedIndex));
            }
            
            if (!subseqsMatched) {
                result += subseqs.get(0).length();
            }
            subseqs.remove(0);
            subseqs.sort(Comparator.comparingInt(String::length));
        }
        result += subseqs.get(0).length();

        System.out.println(result);
    }
}