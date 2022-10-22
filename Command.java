import java.util.ArrayList;

public class Command {
    String commandWord;
    String secondLine;
    ArrayList<String> letters;
    ArrayList<Coordinates> coordinates;

    public Command(String word1, String word2, ArrayList<String> letters, ArrayList<Coordinates> coords){
        commandWord = word1;
        secondLine = word2;
        this.letters = letters;
        coordinates = coords;
    }

    public String getCommandWord(){
        return commandWord;
    }

    public String getSecondWord(){
        return secondLine;
    }

    public ArrayList<String> getLetters(){
        return letters;
    }
    public ArrayList<Coordinates> getCoordinates(){
        return coordinates;
    }


}
