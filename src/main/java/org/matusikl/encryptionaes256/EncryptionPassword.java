package org.matusikl.encryptionaes256;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

public class EncryptionPassword {
    private static final String FORMAT = "UTF8";
    private static final String SECURITY_SCHEME = "DESede";
    private KeySpec keySpec;
    private SecretKeyFactory secretKeyFactory;
    private Cipher cipher;
    byte[] bytes;
    SecretKey secretKey;
    private String encryptionKey;
    private String encryptionScheme;

    public EncryptionPassword() throws Exception{
        encryptionKey = "FirstEncryptionCodeToTrain";
        encryptionScheme = SECURITY_SCHEME;
        bytes = encryptionKey.getBytes(FORMAT);
        keySpec = new DESedeKeySpec(bytes);
        secretKeyFactory = SecretKeyFactory.getInstance(encryptionScheme);
        cipher = Cipher.getInstance(encryptionScheme);
        secretKey = secretKeyFactory.generateSecret(keySpec);
    }

    public String encrypt(String passwordToEncrypt){
        String encryptedPassword = null;
        try{
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] plainText = passwordToEncrypt.getBytes(FORMAT);
            byte[] encryptedPass = cipher.doFinal(plainText);
            byte[] encode = Base64.getEncoder().encode(encryptedPass);
            encryptedPassword = new String(encode);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    public String decrypt(String passwordToDecrypt){
        String decryptedPassword = null;
        try{
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedPassword = Base64.getDecoder().decode(passwordToDecrypt);
            byte[] plainText = cipher.doFinal(encryptedPassword);
            decryptedPassword = new String(plainText);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return decryptedPassword;
    }
}
