import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class BudgetOrganization {
    int cost;
    Boolean costCovered = true;
    ArrayList<Integer> budgets = new ArrayList<Integer>();
    ArrayList<Integer> contributions = new ArrayList<Integer>();
    ArrayList<Integer> remainders = new ArrayList<Integer>();

    public int findHighestBudget(){
        return Collections.max(budgets);
    }

    public int findLowestBudget(){
        int lowestBudget = Collections.min(budgets);
        if (lowestBudget * budgets.size() > cost){
            lowestBudget = 0;
        }
        return lowestBudget;
    }

    public void sortBudgets(){
        System.err.println("sorting Budgets");
        Collections.sort(budgets);
    }

    public void checkIfTotalBudgetCoversCost(){
        System.err.println("checking if total budget covers the cost");
        if (sumList(budgets) < cost){
            System.out.println("IMPOSSIBLE");
            costCovered = false;
        }
    }

    public void setLowestContribution(int lowestContribution) {
        System.err.println("set lowest contribution to all contributors");
        for ( int i = 0 ; i < budgets.size() ; i++ ){
            contributions.add(lowestContribution);
            remainders.add(budgets.get(i) - lowestContribution);
        }
    }

    public void optimizeContributions(){
        System.err.println("optimize the contributions");
        int sumContributions = sumList(contributions);
        while (sumContributions < cost){
            System.err.println("sum of contributions = " + sumContributions);
            for (int i = budgets.size() - 1 ; i >= 0 ; i--){
                if (contributions.get(i) < budgets.get(i)){
                    if (sumContributions + 1 > cost){
                        break;
                    }
                    contributions.set(i, contributions.get(i) + 1);
                    sumContributions++;
                }
            }
            sumContributions = sumList(contributions);
        }
    }

    public int sumList(ArrayList<Integer> liste){
        int sum = 0;
        for ( int i = 0 ; i < liste.size() ; i++){
            sum += liste.get(i);
        }
        return sum;
    }

    public void dumpSolution(){
        for (int i = 0 ; i < contributions.size() ; i++){
            System.out.println(contributions.get(i));
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        BudgetOrganization budgetOrganization = new BudgetOrganization();
        int N = in.nextInt();
        int C = in.nextInt();
        budgetOrganization.cost = C;
        for (int i = 0; i < N; i++) {
            int B = in.nextInt();
            budgetOrganization.budgets.add(B);
        }
        budgetOrganization.checkIfTotalBudgetCoversCost();

        if (budgetOrganization.costCovered){
            budgetOrganization.sortBudgets();
            budgetOrganization.setLowestContribution(budgetOrganization.findLowestBudget());
            budgetOrganization.optimizeContributions();
            budgetOrganization.dumpSolution();
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        
    }
}