import java.util.Scanner;


public class Parser {
    private Scanner userInput;

    public Parser(){
        userInput = new Scanner(System.in);
    }

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
                final Coordinates thing = new Coordinates(Coordinates.toXCoordinate(userIn.next().charAt(0)), Coordinates.toYCoordinate(userIn.next()));
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

        public static void main(String[] args){
            Parser parser = new Parser();
            Command command = parser.getCommand();
            System.out.println(command.getCommandWord());
            System.out.println(command.getSecondWord());
            System.out.println(command.getLetters());
            Coordinates coordinates = command.getCoordinates();
            System.out.println(coordinates.getXCoordinate());
            System.out.println(coordinates.getYCoordinate());
    }

}


