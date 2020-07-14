import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Network {
    ArrayList<Integer> Y = new ArrayList<Integer>();
    int leftBound, rightBound;
    int yMainCable;
    double length = 0;

    public void setYMainCable() {
        int middle = Y.size() / 2;
        middle = middle > 0 && middle % 2 == 0 ? middle - 1 : middle;
        yMainCable = Y.get(middle);
    }

    public void computeLength() {
        length += Math.abs(rightBound - leftBound);
        for (int y : Y) {
            length += Math.abs(y - yMainCable);
        }

    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Network network = new Network();
        network.leftBound = 1073741824;
        network.rightBound = -1073741824;

        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int X = in.nextInt();
            int Y = in.nextInt();
            network.Y.add(Y);
            
            if (X > network.rightBound) {
                network.rightBound = X;
            }

            if (X < network.leftBound) {
                network.leftBound = X;
            }
            
        }

        Collections.sort(network.Y);
        network.setYMainCable();
        network.computeLength();

        System.out.printf("%.0f", network.length);

    }
}