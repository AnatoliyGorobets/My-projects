
/**
 * Write a description of class CaeserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaeserCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0, key);
        int idx = 0;
        for (int i =0; i<encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar)){
                idx = alphabet.indexOf(currChar);
                if(idx != -1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else {
                char currCharUp = currChar;
                idx = alphabet.indexOf(Character.toUpperCase(currCharUp));
                if(idx != -1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testCaeser() {
        int key = 15;
       //FileResource fr = new FileResource();
       //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println("Key is "+key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shftAlphabetOne = alphabet.substring(key1)+alphabet.substring(0, key1);
        String shftAlphabetTwo = alphabet.substring(key2)+alphabet.substring(0, key2);
        String ourShtAlphabet = "";
        int idx = 0;
        for (int i =0; i<encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i%2 == 0) {
                ourShtAlphabet = shftAlphabetOne;
            }
            else {
                ourShtAlphabet = shftAlphabetTwo;
            }
            if (Character.isUpperCase(currChar)){
                idx = alphabet.indexOf(currChar);
                if(idx != -1) {
                    char newChar = ourShtAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else {
                char currCharUp = currChar;
                idx = alphabet.indexOf(Character.toUpperCase(currCharUp));
                if(idx != -1) {
                    char newChar = ourShtAlphabet.charAt(idx);
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
        public void testCaeserTwoKeys() {
        int key1 = 14;
        int key2 = 24;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("Key1 is "+key1);
        System.out.println("Key2 is "+key2);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 26-key1, 26-key2);
        System.out.println(decrypted);
    }
}
