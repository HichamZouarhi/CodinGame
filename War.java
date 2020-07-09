import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    ArrayList<Integer> deck = new ArrayList<Integer>();

    public void addGainedCards(ArrayList<Integer> gainedCards, ArrayList<Integer> ownCards) {
        deck.addAll(ownCards);
        deck.addAll(gainedCards);
    }

    public int playCard() {
        int card = deck.get(0);
        deck.remove(0);
        return card;
    }

    public ArrayList<Integer> playWar() {
        ArrayList<Integer> cards = new ArrayList<Integer>();
        for (int i = 0 ; i < 3 ; i++) {
            if (this.hasCards()) {
                cards.add(this.playCard());
            }
            else {
                break;
            }
        }
        return cards;
    }

    public Boolean hasCards() {
        return deck.size() > 0;
    }
}

class Solution {
    public static String[] CHAR_CARDS = new String[] {"J", "Q", "K", "A"};

    public static void dumpCards(ArrayList<Integer> cardsP1, ArrayList<Integer> cardsP2) {
        System.err.println("------------------------------------------------");
        for (int i = 0 ; i < cardsP1.size() ; i++) {
            System.err.print(cardsP1.get(i) + " ");
        }
        System.err.println("");
        for (int i = 0 ; i < cardsP2.size() ; i++) {
            System.err.print(cardsP2.get(i) + " ");
        }
        System.err.println("");
    }

    public static int convertCardToNumber(String card) {
        switch (card) {
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
        }
        return 0;
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Player player1 = new Player();
        Player player2 = new Player();

        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            String cardValueStr = cardp1.substring(0, cardp1.length() - 1);
            int cardValue = Arrays.asList(CHAR_CARDS).contains(cardValueStr) ? 
                convertCardToNumber(cardValueStr) : Integer.parseInt(cardValueStr);
            player1.deck.add(cardValue);
        }
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            String cardValueStr = cardp2.substring(0, cardp2.length() - 1);;
            int cardValue = Arrays.asList(CHAR_CARDS).contains(cardValueStr) ? 
                convertCardToNumber(cardValueStr) : Integer.parseInt(cardValueStr);
            player2.deck.add(cardValue);
        }
        System.err.println("Initial decks");
        dumpCards(player1.deck, player2.deck);
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.err.println("GAME BEGINS");
        Boolean roundEnd = true;
        int numberOfRounds = 0;
        Boolean PAT = false;
        ArrayList<Integer> cardsPlayed1 = new ArrayList<Integer>(), 
            cardsPlayed2 = new ArrayList<Integer>();
        while (player1.hasCards() && player2.hasCards()) {
            int cardP1 = player1.playCard();
            int cardP2 = player2.playCard();
            
            if (roundEnd) {
                cardsPlayed1 = new ArrayList<Integer>();
                cardsPlayed2 = new ArrayList<Integer>();
            }
            cardsPlayed1.add(cardP1);
            cardsPlayed2.add(cardP2);

            if (cardP1 == cardP2) {
                System.err.println("WAR");
                cardsPlayed1.addAll(player1.playWar());
                cardsPlayed2.addAll(player2.playWar());
                if (!player1.hasCards() || !player2.hasCards()) {
                    PAT = true;
                    break;
                }
                roundEnd = false;
            }
            else if (cardP1 > cardP2) {
                System.err.println("P1 wins");
                player1.addGainedCards(cardsPlayed2, cardsPlayed1);
                roundEnd = true;
                numberOfRounds++;
            }
            else {
                System.err.println("P2 wins");
                player2.addGainedCards(cardsPlayed2, cardsPlayed1);
                roundEnd = true;
                numberOfRounds++;
            }
            System.err.println("round : " + numberOfRounds);
            dumpCards(cardsPlayed1, cardsPlayed2);
            System.err.println("current decks");
            dumpCards(player1.deck, player2.deck);
        }

        if (PAT){
            System.out.println("PAT");
        }
        else if (player1.hasCards() && !player2.hasCards()){
            System.out.println("1 " + numberOfRounds);
        }
        else if (!player1.hasCards() && player2.hasCards()){
            System.out.println("2 " + numberOfRounds);
        }
    }
}