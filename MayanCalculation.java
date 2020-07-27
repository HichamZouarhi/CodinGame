import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Number {
    long value = 0;
    int height;
    ArrayList<String> representation = new ArrayList<String>();

    public void gsetValue(ArrayList<Number> baseNumbers) {
        String valueStr = "";
        int power = representation.size() / height - 1;
        for (int i = 0 ; i < representation.size(); i += height) {
            // for (String line : representation.subList(i, i + height)) {
            //     System.err.println(line);
            // }
            for (int j = 0 ; j < baseNumbers.size() ; j++) {
                
                if (representation.subList(i, i + height).equals(baseNumbers.get(j).representation)) {
                    // valueStr += Integer.toString(j);
                    value += (j * Math.pow(20, power));
                    power--;
                    break;
                }
            }
        }
        //System.err.println(valueStr);
        //value = Integer.parseInt(valueStr, 20);
    }

    public void setRepresentation(ArrayList<Number> baseNumbers) {
        String valueStr = Long.toString(value, 20);
        for (char digit : valueStr.toCharArray()) {
            representation.addAll(baseNumbers.get(Character.getNumericValue(digit)).representation);
        }
    }

    public void dumpNumber() {
        System.err.println(value);
        for (String line: representation) {
            System.out.println(line);
        }
    }


}

class Solution {

    public static long compute(long number1, long number2, String operation) {
        switch(operation) {
            case "+": return number1 + number2;
            case "-": return number1 - number2;
            case "*": return number1 * number2;
            case "/": return number1 / number2;
        }
        return 0;
    }

    public static void dumpBaseNumbers(ArrayList<Number> baseNumbers) {
        System.err.println("Base Numbers ");
        for (Number baseNumber : baseNumbers) {
            System.err.println("value = " + baseNumber.value);
            for (String line: baseNumber.representation) {
                System.err.println(line);
            }
        }
    }

    public static void main(String args[]) {
        ArrayList<Number> baseNumbers = new ArrayList<Number>();
        for (int i = 0; i < 20 ; i++) {
            baseNumbers.add(new Number());
        }
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        for (int i = 0; i < H; i++) {
            String numeral = in.next();
            for (int j = 0 ; j <= numeral.length() - L ; j += L) {
                baseNumbers.get(j / L).representation.add(numeral.substring(j, j + L));
                baseNumbers.get(j / L).value = j / L;
                baseNumbers.get(j / L).height = H;
            }
        }
        dumpBaseNumbers(baseNumbers);

        int S1 = in.nextInt();
        System.err.println("first number");
        Number number1 = new Number();
        number1.height = H;
        for (int i = 0; i < S1; i++) {
            String num1Line = in.next();
            System.err.println(num1Line);
            number1.representation.add(num1Line);
        }
        number1.gsetValue(baseNumbers);
        System.err.println(number1.value);

        int S2 = in.nextInt();
        System.err.println("second number");
        Number number2 = new Number();
        number2.height = H;
        for (int i = 0; i < S2; i++) {
            String num2Line = in.next();
            System.err.println(num2Line);
            number2.representation.add(num2Line);
        }
        number2.gsetValue(baseNumbers);
        System.err.println(number2.value);

        String operation = in.next();
        System.err.println(operation);

        System.err.println("Result");
        Number resultNumber = new Number();
        resultNumber.value = compute(number1.value, number2.value, operation);
        resultNumber.setRepresentation(baseNumbers);
        resultNumber.dumpNumber();

    }
}