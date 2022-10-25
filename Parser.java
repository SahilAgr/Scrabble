import java.util.Scanner;

import javax.tools.StandardJavaFileManager;


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
                if(userIn.hasNext()){
                    word = userIn.next();
                }
            }
            else if (word1.equals("place")){
                if(userIn.hasNext()){
                    word2 = userIn.next();
                }
                Coordinates.xCoordinate xCoord = null;
                if(userIn.hasNext()){
                    xCoord = Coordinates.xCoordinate.toXCoordinate(userIn.next().charAt(0));
                }
                Coordinates.yCoordinate yCoord = null;
                if(userIn.hasNext()){
                    yCoord = Coordinates.yCoordinate.toYCoordinate(userIn.next());
                }
                final Coordinates thing = new Coordinates(xCoord, yCoord);
                coordinates = thing;
                if (userIn.hasNext()){
                    word = userIn.next();
                }

            }
            else{
                word1 = null;
            }
        }
        userIn.close();
        return new Command(word1, word2, word, coordinates);
    }
}


