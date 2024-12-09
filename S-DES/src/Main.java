import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String plainText = "10100011";
        String key = "1111111111";

//        Scanner scanner = new Scanner(System.in);
//        String plainText = SDES.ScanInput(scanner, 8,  "plain text");
//        String key = SDES.ScanInput(scanner, 10,  "key");
//        scanner.close();

        SDES sdes = new SDES(plainText, key);
        String result = sdes.alg();
        System.out.println("result = " + result);
    }


}