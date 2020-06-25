import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        String[][] tunnels = new String[H][W];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            int j = 0;
            for (String ch : LINE.split(" ")) { 
                tunnels[i][j] = ch;
                j++;
            }
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();
            
            System.err.println(XI + " " + YI + " - " + POS);
            
            String currentRoom = tunnels[YI][XI];
            System.err.println(currentRoom);
            
            if (currentRoom.equals("1")) {
                YI++;   
            }
            else if (currentRoom.equals("2")){
                if (POS.equals("LEFT")){
                    XI++;
                }
                else if (POS.equals("RIGHT")){
                    XI--;
                }
            }
            else if (currentRoom.equals("3")){
                if (POS.equals("TOP")){
                    YI++;
                }
            }
            else if (currentRoom.equals("4")){
                if (POS.equals("TOP")){
                    XI--;
                }
                else if (POS.equals("RIGHT")){
                    YI++;
                }
            }
            else if (currentRoom.equals("5")){
                if (POS.equals("TOP")){
                    XI++;
                }
                else if (POS.equals("LEFT")){
                    YI++;
                }
            }
            else if (currentRoom.equals("6")){
                if (POS.equals("LEFT")){
                    XI++;
                }
                else if (POS.equals("RIGHT")){
                    XI--;                
                }
            }
            else if (currentRoom.equals("7")){
                YI++;
            }
            else if (currentRoom.equals("8")){
                YI++;
            }
            else if (currentRoom.equals("9")){
                YI++;
            }
            else if (currentRoom.equals("10")){
                if (POS.equals("TOP")){
                    XI--;
                }
            }
            else if (currentRoom.equals("11")){
                if (POS.equals("TOP")){
                    XI++;
                }
            }
            else if (currentRoom.equals("12")){
                YI++;
            }
            else if (currentRoom.equals("13")){
                YI++;
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println(XI + " " + YI);
        }
    }
}