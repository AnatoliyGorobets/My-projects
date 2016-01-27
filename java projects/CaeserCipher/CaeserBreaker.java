
/**
 * Write a description of class CaeserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaeserBreaker {
    public String decrypt(String encrypted){
        CaeserCipher cc = new CaeserCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex<4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }
    
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k<vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k<message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public void testdecrypt() {
        FileResource resource = new FileResource();
        String decrypt = decrypt(resource.asString());
        System.out.println(decrypt);
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for(int k = start; k<message.length(); k++) {
            char ch = message.charAt(k);
            half.append(ch);
            k +=1;
        }
        return (half.toString());
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex<4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey; 
    }
    
    
    public void decryptTwoKeys(String encrypted) {
        CaeserCipher cc = new CaeserCipher();
        String firstPart = halfOfString(encrypted, 0);
        int key1 = getKey(firstPart);
        String secondPart = halfOfString(encrypted, 1);
        int key2 = getKey(secondPart);
        String decrypt = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println(encrypted);
        System.out.println("The key1 is: "+key1);
        System.out.println("The key2 is: "+key2);
        System.out.println(decrypt);
    }
    
        public void testdecryptTwoKeys() {
        FileResource resource = new FileResource();
        decryptTwoKeys(resource.asString());
        //decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
    }
}
