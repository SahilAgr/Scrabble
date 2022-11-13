mport java.io.*;
import java.util.*;

/**
 * This is the Dictionary Class.
 * For Milestone 2, Reading the text file and checking the word
 *
 * @author Anirudh Bakshi (101158699)
 * @version 2.0
 */
public class Dictionary {

    static boolean wordFound = false;  // Default found word value

    /**
     * Checking the dictionary if the word is legal or not
     */
    public static boolean isLegalWord(String word){

        InputStream input = Dictionary.class.getResourceAsStream("Words.txt");  // File name(Words.txt) in src folder
        InputStreamReader inputReader = new InputStreamReader(input);

        BufferedReader br = new BufferedReader(inputReader); // Buffer object
        String line = null;

        try {

            // Read line by line
            while ((line = br.readLine()) != null){
                if (line.equalsIgnoreCase(word)){
                    return true;  // Word found in the dictionary
                }
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
        return false;  // Word not found
    }

    public static void main(String[] args) {
    }

}
