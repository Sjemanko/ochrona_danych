package zad_1;

public class Main {
    public static void main(String[] args) {

        // zad_1
        Caesar caesar = new Caesar(16);
        Vigenere vigenere = new Vigenere("key");

        String text = "TomAsZBulEckI";

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

    }
}