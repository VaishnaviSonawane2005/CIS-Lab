import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AES_Algorithm {
    public static void main(String[] args) throws Exception {
        String plaintext = "This is a secret msg!";

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();

        byte[] iv = new byte[16];
        java.security.SecureRandom random = new java.security.SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // --- Encrypt ---
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes());

        System.out.println("AES Key      : " + Base64.getEncoder().encodeToString(key.getEncoded()));
        System.out.println("AES IV       : " + Base64.getEncoder().encodeToString(iv));
        System.out.println("Ciphertext   : " + Base64.getEncoder().encodeToString(ciphertext));

        // --- Decrypt ---
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decrypted = cipher.doFinal(ciphertext);

        System.out.println("Decrypted    : " + new String(decrypted));
    }
}