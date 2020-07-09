using System;
using System.Linq;
using System.IO;
using System.Text;
using System.Collections;
using System.Collections.Generic;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution
{
    public static void dumpGrid(List<Group> rows) {
        Console.Error.WriteLine("Grid ----------------");
        for (int i = 0 ; i < rows.Count ; i++) {
            for (int j = 0 ; j < 9 ; j++) {
                Console.Error.Write(rows[i].values[j] + " ");
            }
            Console.Error.Write("\n");
        }
    }

    public static void dumpConversion(int i, int j, int i_, int j_) {
        Console.Error.WriteLine(i + " , " + j + " ==> " + i_ + " , " + j_);
    }
    public static Tuple<int, int> getPositionOnGrid(int i, int j) {
        if ((i + 1) % 6 >= 0 && i >= 6) {
            if ((j + 1) % 6 >= 0 && j >= 6) {
                return Tuple.Create(8, ((i % 6) * 3) + (j % 6)); 
            }
            else if ((j + 1) % 3 >= 0 && j >= 3) {
                return Tuple.Create(7, ((i % 6) * 3) + (j % 3)); 
            }
            else {
                return Tuple.Create(6, ((i % 6) * 3) + j);
            }
        }
        else if ((i + 1) % 3 >= 0 && i >= 3) {
            if ((j + 1) % 6 >= 0 && j >= 6) {
                return Tuple.Create(5, ((i % 3) * 3) + (j % 6)); 
            }
            else if ((j + 1) % 3 >= 0 && j >= 3) {
                return Tuple.Create(4, ((i % 3) * 3) + (j % 3)); 
            }
            else {
                return Tuple.Create(3, ((i % 3) * 3) + j);
            }
        }
        else {
            if ((j + 1) % 6 >= 0 && j >= 6) {
                return Tuple.Create(2, (i * 3) + (j % 6)); 
            }
            else if ((j + 1) % 3 >= 0 && j >= 3) {
                return Tuple.Create(1, (i * 3) + (j % 3)); 
            }
            else {
                return Tuple.Create(0, (i * 3) + j);
            }
        }


        
    } 
    static void Main(string[] args)
    {
        List<Group> rows = Enumerable.Range(1, 9).Select(i => new Group()).ToList();
        List<Group> cols = Enumerable.Range(1, 9).Select(i => new Group()).ToList();
        List<Group> grids = Enumerable.Range(1, 9).Select(i => new Group()).ToList();

        for (int i = 0; i < 9; i++)
        {   
            
            string[] inputs = Console.ReadLine().Split(' ');
            for (int j = 0; j < 9; j++)
            {
                int n = int.Parse(inputs[j]);
                Console.Error.Write(n + " ");

                // fill rows
                rows[i].values[j] = n;
                // fill columns
                cols[j].values[i] = n;

                // fill grids
                Tuple<int, int> gridPos = getPositionOnGrid(i, j);
                
                grids[gridPos.Item1].values[gridPos.Item2] = n;
                //dumpConversion(i, j, gridPos.Item1, gridPos.Item2);
            }
            Console.Error.Write("\n");
            
        }

        dumpGrid(rows);

        List<Group> grid = new List<Group>();
        grid.AddRange(rows);
        grid.AddRange(cols);
        grid.AddRange(grids);

        bool state = true;
        for (int i = 0 ; i < grid.Count ; i++) {
            if (!grid[i].isValid()){
                state = false;
                break;
            }
        }

        var result = state ? "true" : "false";
        Console.WriteLine(result);
    }
}

class Group {
    public List<int> values;

    public Group() {
        values = new List<int>(new int[9]);
    }
    

    public bool isValid(){
        values.Sort();

        for (int i = 1 ; i < values.Count ; i++) {
            if (values[i] != values[i - 1] + 1) {

                return false;
            }
        }
        return true;
    }
}