import java.io.*;
import java.util.*;

/**
 * This is the Dictionary Class.
 * For Milestone 1, Reading the text file and checking the word
 *
 * @author Anirudh Bakshi (101158699)
 * @version 1.0
 */
public class Dictionary {
    final static String filePath = "/Users/anirudhbakshi/Desktop/Words.txt";  // Text file (Dictionary)
    static boolean wordFound  = false;

    /**
     * Checking the dictionary
     */
    public static Map<String, String> HashMapFromTextFile(){
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader br = null;

        String word = "abuse";  // Test word

        try {
            File file = new File(filePath);  // File Object
            br = new BufferedReader(new FileReader(file));  // Buffered Object
            String line = null;

            // Read line by line
            while ((line = br.readLine()) != null){
                if (line.equalsIgnoreCase(word)){
                    System.out.println("Yay");  // Found the word
                    wordFound = true;
                    break;  // Break the loop
                }

            }

            if (wordFound != true){
                System.out.println("nah");  // Word not found
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {

            // Closing BufferedReader
            if(br != null){
                try {
                    br.close();
                }
                catch (IOException e){

                };
            }
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String, String> mapFromFile = HashMapFromTextFile();  // Read text to HashMap

        // Iterate HashMap entries
        for (Map.Entry<String, String> entry : mapFromFile.entrySet()){
        }
    }

}
