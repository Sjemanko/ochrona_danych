package zad_1;

import java.util.*;

public class CaesarCracker {
    private final ArrayList<Character> letterRank = new ArrayList<>() {
        {
            add('a');
            add('i');
            add('e');
            add('o');
            add('n');
            add('z');
            add('r');
            add('s');
            add('w');
            add('y');
        }
    };
    final private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    Caesar caesar = new Caesar(0);


    public void crack(String text) {
       HashMap<Character, Integer> pairs = countLetters(text);
       Collection<Integer> values = pairs.values();
       int maxValue = Collections.max(values);
       long numberOfMaxValues = values.stream().filter(v -> v == maxValue).count();
       //TODO: case where one or two numbers are the numbersOfMaxValues

       //brute force
       shiftByAllLettersRank(text);
    }

    private HashMap<Character, Integer> countLetters(String text) {
        HashMap<Character, Integer> table = new HashMap<>();
        char[] text_arr = text.toCharArray();
        for (char c : text_arr) {
            table.put(c, table.getOrDefault(c, 0) + 1);
        }
        return table;
    }

    private void shiftByAllLettersRank(String text) {
        String strAlphabet = new String(alphabet);
        String changedText = "";
        for (int i = 0; i < letterRank.size(); i++) {
            char letter_in_rank = letterRank.get(i);
            int index_of_rank_letter_in_alph = strAlphabet.indexOf(letter_in_rank);

            for(int j = 0; j < text.length(); j++) {
                int letter = text.charAt(j);
                int index_of_letter_in_alph = strAlphabet.indexOf(letter);
                int index_diff = Math.abs((index_of_letter_in_alph - index_of_rank_letter_in_alph) % 26);
                caesar.setKey(index_diff);
                changedText = caesar.cipher(text, 1);
                System.out.println("litera ze słownika: " + letter_in_rank + ", litera: " + (char)letter + " przesunięta o: " + index_diff + "-> otrzymany szyfr: " + changedText);
            }
            System.out.println(" ");

        }

    }

}
