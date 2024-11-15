package zad_1;

public class Main {
    public static void main(String[] args) {

        // zad_1
        Caesar caesar = new Caesar(7);
        Vigenere vigenere = new Vigenere("key");
        CaesarCracker cracker = new CaesarCracker();

        String text = "The transformation can be represented by aligning two alphabets; the cipher alphabet is the plain alphabet rotated left or right by some number of positions. For instance, here is a Caesar cipher using a left rotation of three places, equivalent to a right shift of";

        String encodedCaesar = caesar.cipher (text, 0);
        System.out.println(encodedCaesar);

        String decodedCaesar = caesar.cipher (encodedCaesar, 1);
        System.out.println(decodedCaesar);

        System.out.println();

        // zad_2
        String encodedVigenere = vigenere.cipher(text, 0);
        System.out.println(encodedVigenere);

        String decodedVigenere = vigenere.cipher(encodedVigenere, 1);
        System.out.println(decodedVigenere);

        System.out.println();
//        // zad_3
        String textToDecodeByCracker = "qlkuh g uhqwyvzazgfjo aljoupr zgfmyvdhuph. Qlza av yvkghq zgfmyb wvkzahdplupvdlnv, d raóyft rhżkh spalyh alrzab qhdulnv (uplghzgfmyvdhulnv) ghzaęwvdhuh qlza puuą";
        cracker.crack(textToDecodeByCracker);
    }
}