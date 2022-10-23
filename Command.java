import java.util.ArrayList;

public class Command {
    String commandWord;
    String secondLine;
    String letters;
    ArrayList<Coordinates> coordinates;

    public Command(String word1, String word2, String word, ArrayList<Coordinates> coords){
        commandWord = word1;
        secondLine = word2;
        this.letters = word;
        coordinates = coords;
    }

    public String getCommandWord(){
        return commandWord;
    }

    public String getSecondWord(){
        return secondLine;
    }

    public String getLetters(){
        return letters;
    }
    public ArrayList<Coordinates> getCoordinates(){
        return coordinates;
    }
    public boolean invalidCommand(){
        if (commandWord == null){
            return true;
        }
        return false;
    }

}
