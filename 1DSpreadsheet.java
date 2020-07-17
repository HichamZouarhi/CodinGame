import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class SpreadSheet {
    
    ArrayList<String> cells = new ArrayList<String>();

    public String evaluateArg(String arg) {
        if (arg.contains("$")) {
            arg = evaluateCell(Integer.parseInt(arg.substring(1)));
        }
        return arg;
        
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String evaluateCell(int cellIndex) {
        String op = cells.get(cellIndex).split(" ")[0];
        String value = cells.get(cellIndex);
        System.err.println("evaluating cell " + cellIndex + " : " + value);

        if (!isNumeric(op)) {
            String arg1 = cells.get(cellIndex).split(" ")[1];
            if (op.equals("VALUE")) {
                value = evaluateArg(arg1);
                cells.set(cellIndex, value);
                return value;
            }
            else {
                String arg2 = cells.get(cellIndex).split(" ")[2];
                arg1 = evaluateArg(arg1);
                arg2 = evaluateArg(arg2);

                if (op.equals("ADD")) {
                    value = Integer.toString(Integer.parseInt(arg1) + Integer.parseInt(arg2));
                    cells.set(cellIndex, value);
                    return value;
                }
                else if (op.equals("SUB")) {
                    value = Integer.toString(Integer.parseInt(arg1) - Integer.parseInt(arg2));
                    cells.set(cellIndex, value);
                    return value;
                }
                else if (op.equals("MULT")) {
                    value = Integer.toString(Integer.parseInt(arg1) * Integer.parseInt(arg2));
                    cells.set(cellIndex, value);
                    return value;
                }
            }
        }
        return value;
    }

}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        SpreadSheet spreadsheet = new SpreadSheet();
        for (int i = 0; i < N; i++) {
            String operation = in.next();
            String arg1 = in.next();
            String arg2 = in.next();
            spreadsheet.cells.add(operation + " " + arg1 + " " + arg2);
        }
        for (int i = 0; i < N; i++) {

            // Write an answer using System.out.println()
            // To debug: System.err.println("Debug messages...");

            spreadsheet.evaluateCell(i);
            System.out.println(spreadsheet.cells.get(i));
        }
    }
}