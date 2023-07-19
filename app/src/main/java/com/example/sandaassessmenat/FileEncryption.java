package com.example.sandaassessmenat;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class FileEncryption {
    private static final int CHUNK_SIZE = 1048576; // 1MB

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path of the input file: ");
        String inputFile = scanner.nextLine();
        String encryptedFile = "encryptedtext";
        String decryptedFile = "decryptedtext";

        try {
            // Generate a random key
            SecureRandom secureRandom = new SecureRandom();
            byte[] keyBytes = new byte[16];
            secureRandom.nextBytes(keyBytes);
            String key = Base64.getEncoder().encodeToString(keyBytes);

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // Encrypt the file
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptFile(inputFile, encryptedFile, cipher);

            // Decrypt the file
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptFile(encryptedFile, decryptedFile, cipher);

            System.out.println("File encryption and decryption completed.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please provide a valid file path.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void encryptFile(String inputFile, String outputFile, Cipher cipher) throws IOException, IllegalBlockSizeException, BadPaddingException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(inputFile));
             OutputStream outputStream = Files.newOutputStream(Paths.get(outputFile));
             CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {

            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
                cipherOutputStream.write(encryptedBytes);
            }

            byte[] finalBytes = cipher.doFinal();
            cipherOutputStream.write(finalBytes);
        }
    }

    private static void decryptFile(String inputFile, String outputFile, Cipher cipher) throws IOException, IllegalBlockSizeException, BadPaddingException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(inputFile));
             OutputStream outputStream = Files.newOutputStream(Paths.get(outputFile));
             CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher)) {

            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;

            while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
                outputStream.write(decryptedBytes);
            }

            byte[] finalBytes = cipher.doFinal();
            outputStream.write(finalBytes);
        }
    }
}

