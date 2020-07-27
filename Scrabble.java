import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Word {
    String word;
    int score = 0;

    public Boolean hasLetters(HashMap<Character, Integer> letters) {
        for (char c: word.toCharArray()) {
            if (letters.containsKey(c)) {
                if (word.length() - word.replace(String.valueOf(c), "").length() > letters.get(c)) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
}
class Solution {
    public static int getLetterScore(char c) {
        if ("eaionrtlsu".indexOf(c) != -1) {
            return 1;
        }
        else if ("dg".indexOf(c) != -1) {
            return 2;
        }
        else if ("bcmp".indexOf(c) != -1) {
            return 3;
        }
        else if ("fhvwy".indexOf(c) != -1) {
            return 4;
        }
        else if ('k' == c) {
            return 5;
        }
        else if ("jx".indexOf(c) != -1) {
            return 8;
        }
        else {
            return 10;
        }
    }

    public static int getWordScore(String word) {
        int score = 0;
        for (char c: word.toCharArray()) {
            score += getLetterScore(c);
        }
        return score;
    }

    public static void main(String args[]) {
        
        Scanner in = new Scanner(System.in);
        ArrayList<Word> words = new ArrayList<Word>();
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String W = in.nextLine();
            Word word = new Word();
            word.word = W;
            word.score = getWordScore(W);
            words.add(word);
        }

        Collections.sort(words, (word1, word2) -> word2.score - word1.score);
        String LETTERS = in.nextLine();
        // words = words.stream()
        //       .filter(word -> word.word.length() > LETTERS.length())
        //       .collect(Collectors.toList());
        
        HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
        for(char c: LETTERS.toCharArray()) {
            if (letters.containsKey(c)) {
                letters.put(c, letters.get(c) + 1);
            }
            else {
                letters.put(c, 1);
            }
        }

        for (Word word: words) {
            if (word.word.length() > LETTERS.length()) {
                continue;
            }
            if (word.hasLetters(letters)) {
                System.out.println(word.word);
                break;
            }
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}