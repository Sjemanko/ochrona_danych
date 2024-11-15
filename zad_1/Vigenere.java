package zad_1;

public class Vigenere {
    private String key;
    final private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Vigenere(String key) {
        this.key = key;
    }

    public String cipher(String text, int mode) {
        String str_alphabet = new String(alphabet);
        char[] changedText = new char [text.length()];
        char[] text_arr = text.toLowerCase().toCharArray();
        char[] key_arr = key.toCharArray();
        if(mode == 0) {
            for (int i = 0; i < key.length(); i++) {
                for (int j = i; j < text_arr.length; j += key_arr.length) {
                    if(!(Character.isLetterOrDigit(text_arr[j]))) {
                        changedText[j] = text_arr[j];
                        continue;
                    }
                    int new_index = (str_alphabet.indexOf(text_arr[j]) + str_alphabet.indexOf(key_arr[i])) % 26;
                    changedText[j] = str_alphabet.charAt(new_index);
                    if(i+j > text.length()) {
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < key.length(); i++) {
                for (int j = i; j < text_arr.length; j += key_arr.length) {
                    if(!(Character.isLetterOrDigit(text_arr[j]))) {
                        changedText[j] = text_arr[j];
                        continue;
                    }
                    int new_index = (str_alphabet.indexOf(text_arr[j]) - str_alphabet.indexOf(key_arr[i])) % 26;
                    if(new_index < 0) {
                        new_index = 26 + new_index;
                    }
                    changedText[j] = str_alphabet.charAt(new_index);
                    if(i+j > text.length()) {
                        break;
                    }
                }
            }
        }
        return new String(changedText);
    }

    public void setKey(String key) {
        this.key = key;
    }
}