import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

public class TestJunit {

    @ParameterizedTest
    @CsvSource({
            "11110111, 1010000010, 00000100",
            "10011001, 1010101010, 00011000",
            "11111111, 1111111111, 00001111",
            "00000000, 1111100000, 00011110",
            "00001111, 1111100000, 10100101",
            "10100101, 1111111111, 01010100",
            "10100011, 1111111111, 11011111",
            "10010110, 1000110001, 10010001",
            "01011011, 1111111111, 01000000",
            "10010110, 1011010010, 01001110",
            "00000000, 0000000000, 11110000",
            "11101010, 0111111101, 10100010",
            "00010011, 0111111101, 01111100",
            "11001100, 0111111101, 10100100",
            "00110011, 1100110001, 00100011",
            "00100011, 1100110001, 00000100",
            "00110011, 1100110011, 00110111",
            "10000001, 1000000001, 01100100",
            "11011011, 1011010010, 01100100",
            "00110000, 1011010010, 10100010",
            "01010100, 1011010010, 10011111",
            "01000110, 1101110100, 00100110",
            "00011101, 1101110100, 10111111",
            "10101001, 1101110100, 00001101",
            "10010000, 0111111111, 11000000"
    })
    void test(String plainText, String key, String expected){
        SDES sdes = new SDES(plainText, key);
        assertEquals(expected,sdes.alg());
    }
}