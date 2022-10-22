import java.util.Scanner;

import java.util.ArrayList;

public class Parser {
    private Scanner userInput;

    public Parser(){
        userInput = new Scanner(System.in);
    }

    public Command getCommand(){
        String word1 = null;
        String word2 = null;
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        ArrayList<String> letters = new ArrayList<>();
        String inputLine;
       
        inputLine = userInput.nextLine();
        
        Scanner userIn = new Scanner(inputLine);
        if(userIn.hasNext()){
            word1 = userIn.next();
            word1.toLowerCase();
            if(word1.equals("help") || (word1.equals("pass"))){
            }
            else if (word1.equals("shuffle")){
                while(userIn.hasNext()){
                    letters.add(userIn.next());
                }
            }
            else if (word1.equals("place")){
                word2 = userIn.next();
                int i = 0;
                while(userIn.hasNext()){
                    final Coordinates thing = new Coordinates(Coordinates.toXCoordinate(userIn.next().charAt(0)), Coordinates.toYCoordinate(userIn.next()));
                    coordinates.add(i, thing);
                    letters.add(userIn.next());
                    i++;
                }
            }
            /*else if (word1.equals("test")){
                int i = 0;
                while(userIn.hasNext()){
                    final Coordinates thing = new Coordinates(Coordinates.toXCoordinate(userIn.next().charAt(0)), Coordinates.toYCoordinate(userIn.next()));
                    System.out.println(thing.getXCoordinate());
                    System.out.println(thing.getYCoordinate());
                    coordinates.add(i, thing);
                }
            }*/
            else{
                word1 = null;
            }
        }
        userIn.close();
        return new Command(word1, word2, letters, coordinates);
    } 

        public static void main(String[] args){
            Parser parser = new Parser();
            Command command = parser.getCommand();
            System.out.println(command.getCommandWord());
            System.out.println(command.getSecondWord());
            System.out.println(command.getLetters());
            ArrayList<Coordinates> coordinates = command.getCoordinates();
            for(int i = 0; i < coordinates.size(); i++){
                System.out.println(coordinates.get(i).getXCoordinate());
                System.out.println(coordinates.get(i).getYCoordinate());
            }
    }

}


