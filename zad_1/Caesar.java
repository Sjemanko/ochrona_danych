package zad_1;

public class Caesar {
    private int key;

    // English Alphabet
    final private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Caesar(int key) {
        this.key = key;
    }

    public String cipher (String text, int mode) {
        StringBuilder changedText = new StringBuilder();
        int offset = 32;
        boolean isUpper = false;
        int asciiShiftedLetter;
        char[] text_arr = text.toCharArray();
        for(int i = 0; i < text.length(); i++) {
            if(Character.isUpperCase(text_arr[i])) {
                isUpper = true;
                text_arr[i] += (char) offset;
            }
            if(!((int)text_arr[i] <= (int)'z' && (int)text_arr[i] >= 'a') || ((int)text_arr[i] >= (int)'A') && ((int)text_arr[i] <= (int)'Z')) {
                changedText.append(text_arr[i]);
                continue;
            }
            int char_index = new String(alphabet).indexOf((text_arr[i]));
            if(mode == 1) {
                asciiShiftedLetter = char_index + (int) 'a' - key;
                if(asciiShiftedLetter < (int)'a') {
                    // case when letter is out of alphabet from left side ('a')
                    asciiShiftedLetter += 'z';
                    asciiShiftedLetter = (asciiShiftedLetter % (int)'a') + (int)'a' + 1;
                }
            } else {
                asciiShiftedLetter = char_index + (int) 'a' + key;
                if(asciiShiftedLetter > (int)'z') {
                    // case when letter is out of alphabet from right side ('z')
                    asciiShiftedLetter = (asciiShiftedLetter % (int)'z') + (int)'a' - 1;
                }
            }
            if(isUpper) {
                asciiShiftedLetter -= 32;
                isUpper = false;
            }
            changedText.append((char)asciiShiftedLetter);
        }
        return changedText.toString();
    }

    public void setKey(int key) {
        this.key = key;
    }

}

