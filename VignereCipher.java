import java.util.Scanner;

public class VignereCipher {
    public static String removeSpaces(String input) {
        return input.replace(" ", "");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the String: ");
        String PlainText = s.nextLine();
        String TEXT = PlainText.toUpperCase();

        System.out.println("Enter the Key: ");
        String Key = s.nextLine();
        String key = Key.toUpperCase();

        String PT = removeSpaces(TEXT);
        int n = PT.length();

        int[] PValue = new int[n];
        int[] KeyValue = new int[key.length()];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            PValue[i] = PT.charAt(i) - 'A';
        }

        for (int i = 0; i < key.length(); i++) {
            KeyValue[i] = key.charAt(i) - 'A';
        }

        for (int i = 0; i < n; i++) {
            int keyChar = KeyValue[i % key.length()];
            int cipherChar = (PValue[i] + keyChar) % 26;
            result.append((char) (cipherChar + 'A'));
        }
        System.out.println("Encrypted Text: " + result.toString());
    }
}