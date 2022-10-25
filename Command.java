/**
 * The Command Class
 */
public class Command {
    String commandWord;
    String secondLine;
    String letters;
    Coordinates coordinates;

    /**
     * The constructor for the command class
     * @param word1 String
     * @param word2 String
     * @param word String
     * @param coords Coordinates
     */
    public Command(String word1, String word2, String word, Coordinates coords){
        commandWord = word1;
        secondLine = word2;
        this.letters = word;
        coordinates = coords;
    }

    /**
     * Returns THe command that was used
     * @return String
     */
    public String getCommandWord(){
        return commandWord;
    }

    /**
     * Returns the second word
     * @return String
     */
    public String getSecondWord(){
        return secondLine;
    }

    /**
     * Returns the letters
     * @return String
     */
    public String getLetters(){
        return letters;
    }

    /**
     * Returns the coordinates
     * @return Coordinates
     */
    public Coordinates getCoordinates(){
        return coordinates;
    }

    /**
     * If command word is null it will return a true for an invalid command
     * @return boolean
     */
    public boolean invalidCommand(){
        if (commandWord == null){
            return true;
        }
        return false;
    }

}
