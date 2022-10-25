import java.util.Scanner;

/**
 * Parser Class for the user inputs
 */
public class Parser {
    private Scanner userInput;

    /**
     * The constructor for the class
     */
    public Parser(){
        userInput = new Scanner(System.in);
    }

    /**
     * Returns the command that was input by the user
     * @return Command
     */
    public Command getCommand(){
        String word1 = null;
        String word2 = null;
        Coordinates coordinates = null;
        String word = null;
        String inputLine;
       
        inputLine = userInput.nextLine();
        
        Scanner userIn = new Scanner(inputLine);
        if(userIn.hasNext()){
            word1 = userIn.next();
            word1.toLowerCase();
            if(word1.equals("help") || (word1.equals("pass"))){
            }
            else if (word1.equals("shuffle")){
                word = userIn.next();
            }
            else if (word1.equals("place")){
                word2 = userIn.next();
                final Coordinates thing = new Coordinates(Coordinates.xCoordinate.toXCoordinate(userIn.next().charAt(0)), Coordinates.yCoordinate.toYCoordinate(userIn.next()));
                coordinates = thing;
                word = userIn.next();

            }
            else{
                word1 = null;
            }
        }
        userIn.close();
        return new Command(word1, word2, word, coordinates);
    } 

}


