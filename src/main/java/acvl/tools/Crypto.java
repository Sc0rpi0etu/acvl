package acvl.tools;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
    public String cryptOrDecrypt(int mode, String password, String text) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {

        SecretKey secret = generateSecretKeyWithPassword(password);

        /* Utilisation de l'algorithme AES */
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(mode, secret);

        /* Lecture du contenu au format byte */
        byte[] content = text.getBytes();

        /* Chiffre ou déchiffre le contenu du fichier */
        byte[] resultContent = aesCipher.doFinal(content);

        return resultContent.toString();
    }

    /**
     * Génère une clé secrète avec le mot de passe
     *
     * @param password Le mot de passe à transformer en clé de chiffrement
     * @return La clé générée à partir du mot de passe
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private SecretKey generateSecretKeyWithPassword(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        /* une clé de 256 bits pour l'algorithme AES-256 */
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        /*
         * On prend les bytes du fichier. Le charset UTF-8 est important en cas
         * d'accentuation des caractères dans le mot de passe
         */
        byte[] key = sha.digest(password.getBytes("UTF-8"));
        SecretKey secret = new SecretKeySpec(key, "AES");
        return secret;
    }

}
