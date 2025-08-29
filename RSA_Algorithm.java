import java.math.BigInteger;
import java.util.Scanner;

public class RSA_Algorithm {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            // Step 1: Input p and q
            System.out.print("Enter prime number p: ");
            BigInteger p = sc.nextBigInteger();

            System.out.print("Enter prime number q: ");
            BigInteger q = sc.nextBigInteger();
            sc.nextLine(); // consume newline

            // Step 2: Compute n and phi
            BigInteger n = p.multiply(q);
            BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

            // Step 3: Choose e such that 1 < e < phi and gcd(e, phi) = 1
            BigInteger e = BigInteger.valueOf(2);
            while (e.compareTo(phi) < 0) {
                if (phi.gcd(e).equals(BigInteger.ONE)) {
                    break; // found valid e
                }
                e = e.add(BigInteger.ONE);
            }

            // Step 4: Compute d = e^(-1) mod phi
            BigInteger d = e.modInverse(phi);

            // Print key values
            System.out.println("\np = " + p);
            System.out.println("q = " + q);
            System.out.println("n = " + n);
            System.out.println("phi(n) = " + phi);
            System.out.println("e (public exponent) = " + e);
            System.out.println("d (private exponent) = " + d);

            // Step 5: Message input
            System.out.print("\nEnter a string message: ");
            String message = sc.nextLine();

            // Encryption
            BigInteger[] encryptedChars = new BigInteger[message.length()];
            char[] decryptedChars = new char[message.length()];

            System.out.println("\n--- Encryption ---");
            for (int i = 0; i < message.length(); i++) {
                int ascii = (int) message.charAt(i);
                BigInteger m = BigInteger.valueOf(ascii);
                BigInteger c = m.modPow(e, n); // encryption
                encryptedChars[i] = c;
                System.out.println(message.charAt(i) + " -> ASCII " + ascii + " -> Encrypted " + c);
            }

            // Decryption
            System.out.println("\n--- Decryption ---");
            for (int i = 0; i < encryptedChars.length; i++) {
                BigInteger decrypted = encryptedChars[i].modPow(d, n); // decryption
                decryptedChars[i] = (char) decrypted.intValue();
                System.out.println("Encrypted " + encryptedChars[i] +
                        " -> Decrypted ASCII " + decrypted +
                        " -> " + decryptedChars[i]);
            }

            System.out.println("\nDecrypted message: " + new String(decryptedChars));

            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}