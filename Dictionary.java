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
    static final String filePath = "C:\\Users\\sahil\\Desktop\\school ish\\y5\\sem1\\3110\\scrabble 1.0\\dictionary.txt";
    static boolean wordFound = false;

    /**
     * Checking the dictionary
     */
    public Dictionary(){
    }

    public boolean isLegalWord(String word){
        word = word.toLowerCase();
        BufferedReader br = null;

        try {
            File file = new File(filePath);  // File Object
            br = new BufferedReader(new FileReader(file));  // Buffered Object
            String line = null;

            // Read line by line
            while ((line = br.readLine()) != null){
                if(word.equals(line)){
                    wordFound = true;
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
        return wordFound;
    }

}
