import java.util.Scanner;

public class SDES {
    public String plainText;
    public String key;

    public SDES(String plainText, String key) {
        this.plainText = plainText;
        this.key = key;
    }


    public static String ScanInput(Scanner scanner, int inputSize, String inputText){
        String text;
        while (true) {
            System.out.printf("Input %s (%d): \n", inputText, inputSize);
            text = scanner.nextLine().replaceAll("\\s+", "");

            if (text.matches("[01]+") && text.length() == inputSize) {
                break;
            } else {
                System.err.println("Error: Invalid input. Try again. Check input size.");
            }
        }
        return text;
    }

    public String MakePermutation(String PermType, String input) {
        char[] originalInput = input.toCharArray();
        char[] result;
        switch(PermType) {
            case "P10":
                result = new char[] {
                    originalInput[2],
                    originalInput[4],
                    originalInput[1],
                    originalInput[6],
                    originalInput[3],
                    originalInput[9],
                    originalInput[0],
                    originalInput[8],
                    originalInput[7],
                    originalInput[5]
            };
                break;
            case "P8":
                result = new char[]{
                    originalInput[5],
                    originalInput[2],
                    originalInput[6],
                    originalInput[3],
                    originalInput[7],
                    originalInput[4],
                    originalInput[9],
                    originalInput[8]
                };
                break;
            case "IP":
                result = new char[] {
                    originalInput[1],
                    originalInput[5],
                    originalInput[2],
                    originalInput[0],
                    originalInput[3],
                    originalInput[7],
                    originalInput[4],
                    originalInput[6]
                };
                break;
            case "EP":
                result = new char[] {
                    originalInput[3],
                    originalInput[0],
                    originalInput[1],
                    originalInput[2],
                    originalInput[1],
                    originalInput[2],
                    originalInput[3],
                    originalInput[0]
                };
                break;
            case "P4":
                result = new char[] {
                    originalInput[1],
                    originalInput[3],
                    originalInput[2],
                    originalInput[0]
                };
                break;
            case "IP-1":
                result = new char[] {
                    originalInput[3],
                    originalInput[0],
                    originalInput[2],
                    originalInput[4],
                    originalInput[6],
                    originalInput[1],
                    originalInput[7],
                    originalInput[5]
                };
                break;
            default:
                System.out.println("Wrong defined permutation. (MakePermutation)");
                throw new Error("Wrong defined permutation");
        }
        return String.valueOf(result);
    }

    public String LS(String key, int shiftAmount) {
        String leftHalf = SplitIntoHalfAndReturnHalfUtil(key, 'L');
        String rightHalf = SplitIntoHalfAndReturnHalfUtil(key, 'R');

        String leftHalfResult = ShiftLeftUtil(leftHalf, shiftAmount);
        String rightHalfResult = ShiftLeftUtil(rightHalf, shiftAmount);

        return leftHalfResult + rightHalfResult;
    }

    public String SplitIntoHalfAndReturnHalfUtil(String input, char returnedHalf) {
        if(returnedHalf == 'L') {
            return input.substring(0, input.length()/2);
        } else {
            return input.substring(input.length()/2);
        }
    }

    public String ShiftLeftUtil(String half, int shiftAmount) {
        String halfResult;
        int leftLength = half.length();

        shiftAmount = shiftAmount % leftLength;
        String leftShiftedPart = half.substring(shiftAmount);
        String leftWrappedPart = half.substring(0, shiftAmount);

        halfResult = leftShiftedPart + leftWrappedPart;
        return halfResult;
    }

    public String XORUtil(String key, String EP) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < key.length(); i++) {
            if(key.charAt(i) != EP.charAt(i)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public String GetValuesFromTables(String left, String right){
        int[][] S0 = new int[][]{
                {1, 0, 3, 2},
                {3, 2, 1, 0},
                {0, 2, 1, 3},
                {3, 1, 3, 2}
        };

        int[][] S1 = new int[][]{
                {0, 1, 2, 3},
                {2, 0, 1, 3},
                {3, 0, 1, 0},
                {2, 1, 0, 3}
        };

        String binValue0 = ConvertTableValuesToBinUtil(left, S0);
        String binValue1 = ConvertTableValuesToBinUtil(right, S1);

        return binValue0 + binValue1;

    }

    public String ConvertTableValuesToBinUtil(String half, int[][] table) {
        char[] halfArr = half.toCharArray();
        char[] row0 = new char[] {halfArr[0], halfArr[3]};
        char[] col0 = new char[] {halfArr[1], halfArr[2]};
        int row0Index = Integer.parseInt(String.valueOf(row0), 2);
        int col0Index = Integer.parseInt(String.valueOf(col0), 2);

        int value = table[row0Index][col0Index];

        String binValue = Integer.toBinaryString(value);
        binValue = String.format("%2s", binValue).replace(' ', '0');
        return binValue;
    }

    public String SplitAndSwap(String input){
        String leftHalf = this.SplitIntoHalfAndReturnHalfUtil(input, 'L');
        String rightHalf = this.SplitIntoHalfAndReturnHalfUtil(input, 'R');

        return rightHalf + leftHalf;
    }
    
    public String alg() {
        System.out.println("KLUCZOWANIE");
        String P10 = this.MakePermutation("P10", key);
        String L1_key = this.LS(P10, 1);
        String K1 = this.MakePermutation("P8", L1_key);
        String L2_key = this.LS(L1_key, 2);
        String K2 = this.MakePermutation("P8", L2_key);
        System.out.println("K1 = " + K1 + "\nK2 = " + K2);

        // SZYFROWANIE

        System.out.println("\nSZYFROWANIE");

        String IPOnPlainText = this.MakePermutation("IP", plainText);
        System.out.println("IP = " + IPOnPlainText);

        String IPOnPlainTextLeft;
        String IPOnPlainTextRight;

        String EP;
        String SwappedConcatIPRightLastOp = "";
        String IPMinus1Perm = "";
        String EPXORKey;
        System.out.println("\n==================================================================");
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                /**
                 * Pierwsza iteracja działa na initial Permutations
                 */
                IPOnPlainTextLeft = this.SplitIntoHalfAndReturnHalfUtil(IPOnPlainText, 'L');
                IPOnPlainTextRight = this.SplitIntoHalfAndReturnHalfUtil(IPOnPlainText, 'R');

                System.out.println("IPOnPlainTextLeft = " + IPOnPlainTextLeft);
                System.out.println("IPOnPlainTextRight = " + IPOnPlainTextRight);

                EP = this.MakePermutation("EP", IPOnPlainTextRight);
                System.out.println("\nEP = " + EP);
                EPXORKey = this.XORUtil(K1, EP);
            } else {
                /**
                 * Druga iteracja działa na wyniku ostatniej operacji 1 iteracji
                 */
                IPOnPlainTextLeft = this.SplitIntoHalfAndReturnHalfUtil(SwappedConcatIPRightLastOp, 'L');
                IPOnPlainTextRight = this.SplitIntoHalfAndReturnHalfUtil(SwappedConcatIPRightLastOp, 'R');

                System.out.println("IPOnPlainTextLeft = " + IPOnPlainTextLeft);
                System.out.println("IPOnPlainTextRight = " + IPOnPlainTextRight);

                EP = this.MakePermutation("EP", IPOnPlainTextRight);
                System.out.println("\nEP = " + EP);
                EPXORKey = this.XORUtil(K2, EP);
            }
            System.out.println("EPXORKey" + i + " = " + EPXORKey + "\n");

            String EPXORKeyLeft = this.SplitIntoHalfAndReturnHalfUtil(EPXORKey, 'L');
            String EPXORKeyRight = this.SplitIntoHalfAndReturnHalfUtil(EPXORKey, 'R');

            System.out.println("EPXORKeyLeft = " + EPXORKeyLeft);
            System.out.println("EPXORKeyRight = " + EPXORKeyRight + "\n");

            this.GetValuesFromTables(EPXORKeyLeft, EPXORKeyRight);

            String S0S1TableResult = this.GetValuesFromTables(EPXORKeyLeft, EPXORKeyRight);
            System.out.println("S0S1TableResult = " + S0S1TableResult);

            String S0S1TableResultP4 = this.MakePermutation("P4", S0S1TableResult);
            System.out.println("S0S1TableResultP4 = " + S0S1TableResultP4 + "\n");

            String IPOnPlainTextLeftXORS0S1TableResultP4 = this.XORUtil(IPOnPlainTextLeft, S0S1TableResultP4);
            System.out.println("IPOnPlainTextLeftXORS0S1TableResultP4 = " + IPOnPlainTextLeftXORS0S1TableResultP4);

            String ConcatIPRightLastOp = IPOnPlainTextLeftXORS0S1TableResultP4 + IPOnPlainTextRight;
            System.out.println("ConcatIPRightLastOp = " + ConcatIPRightLastOp);

            if(i == 0) {
                SwappedConcatIPRightLastOp = this.SplitAndSwap(ConcatIPRightLastOp);
                System.out.println("SwappedConcatIPRightLastOp = " + SwappedConcatIPRightLastOp + "\n");
            } else {
                IPMinus1Perm = this.MakePermutation("IP-1", ConcatIPRightLastOp);
                System.out.println("IPMinus1Perm = " + IPMinus1Perm);
            }
            System.out.println("==================================================================");
        }
//        System.out.println("Result = " + IPMinus1Perm);
        return IPMinus1Perm;
    }
}
