import java.util.*;

public class PlayFairCipher {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = s.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        System.out.print("Enter the key: ");
        String key = s.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        char[][] matrix = generatePlayfairMatrix(key);
        String preparedText = prepareText(text);
        String encrypted = encrypt(preparedText, matrix);

        System.out.println("Encrypted Text: " + encrypted);
    }

    public static char[][] generatePlayfairMatrix(String key) {
        boolean[] used = new boolean[26];
        char[][] matrix = new char[5][5];
        key += "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        for (char c : key.toCharArray()) {
            int idx = c - 'A';
            if (!used[idx] && c != 'J') {
                sb.append(c);
                used[idx] = true;
            }
        }

        for (int i = 0; i < 25; i++) {
            matrix[i / 5][i % 5] = sb.charAt(i);
        }

        return matrix;
    }

    public static String prepareText(String text) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            if (a == b) {
                sb.append(a).append('X');
                i++;
            } else {
                sb.append(a).append(b);
                i += 2;
            }
        }
        if (sb.length() % 2 != 0) {
            sb.append('X');
        }
        return sb.toString();
    }

    public static String encrypt(String text, char[][] matrix) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] pos1 = findPosition(matrix, a);
            int[] pos2 = findPosition(matrix, b);

            if (pos1[0] == pos2[0]) {

                result.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) {


                result.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else {

                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }

        return result.toString();
    }


    public static int[] findPosition(char[][] matrix, char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}