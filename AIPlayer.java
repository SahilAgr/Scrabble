import java.util.*;
import java.util.random.*;

public class AIPlayer extends Player{

    private List<String> test;
    Dictionary dictionary;
    Player player;
    Board board;
    LetterBag letters;
    Random rand;


    Placement placement;

    static final int SIZE = 26;


    public AIPlayer(String name){
        super(name);
        this.rand = new Random();
        this.dictionary = new Dictionary();
        this.player = new Player("Help");
        this.board = new Board();
        this.letters = new LetterBag();

        player.addLettersToHand(letters.getRandomLetters(7));

       /* String testWord = createLetterSet(player.getHand());
        char[] testing = testWord.toCharArray();
        System.out.println(findValidWords(dictionary.allWords(),testing));
*/


    }

    public void playTurn(Game game, Board board, ArrayList<Tile> hand, Player player){
        for(int i = 0; i< 15;i++){
            for(int j = 0; j < 15; j++){
                String playerLetters = createLetterSet(player.getHand());
                char [] playerLetterArray = playerLetters.toCharArray();
                List<String> allPossibleWords = findValidWords(dictionary.allWords(),playerLetterArray);
                Coordinates coord = new Coordinates(Coordinates.xCoordinate.ordinalToXCoordinate(i),Coordinates.yCoordinate.ordinalToYCoordinate(j));
                if(wordOnBoard(board).length() == 0){
                    System.out.println("this is going through " + coord.getYCoordinate().toString() + coord.getXCoordinate().toString());
                    Coordinates middle = new Coordinates(Coordinates.xCoordinate.H, Coordinates.yCoordinate.EIGHT);
                    game.place("right",middle,allPossibleWords.get(rand.nextInt(allPossibleWords.size())),false);
                    return;
                }else{
                    System.out.println("please help me 2");
                    for(int k = 0; k < allPossibleWords.size(); k++){
                        System.out.println("the 3rd for loop");
                        char[] eachWord = allPossibleWords.get(k).toCharArray();
                        char charOnBoard = wordOnBoard(board).charAt(0);
                        if(eachWord.equals(charOnBoard)){
                            if(board.checkPlacement(coord,allPossibleWords.get(k),"right",true, player).isLegalPlace()){
                                System.out.println("hello right");
                                game.place("right",coord,allPossibleWords.get(k),false);
                                return;
                            } else if (board.checkPlacement(coord,allPossibleWords.get(k),"down",true, player).isLegalPlace()) {
                                System.out.println("hello left");
                                game.place("down",coord,allPossibleWords.get(k),false);
                                return;
                            }

                        }
                        }
                    }

                }
            }
        }


    public String createLetterSet(ArrayList<Tile> hand){
        String set = new String();
        String c = wordOnBoard(board);

        String temp = new String();
        for(int i = 0; i < 7; i++) {
            temp = hand.get(i).getString();
            set+=temp;
            System.out.println(temp);
        }
        if(c == ""){
            return set;
        }
        else {
            temp += c;
            return set;
        }



    }


    public String wordOnBoard(Board board){
        String reeee = "";
        for(int i = 0; i< 15;i++){
            for(int j = 0; j < 15; j++){
                Coordinates coord = new Coordinates(Coordinates.xCoordinate.ordinalToXCoordinate(i),Coordinates.yCoordinate.ordinalToYCoordinate(j));
                if(!board.checkFree(coord)){
                    reeee += board.getLetter(coord);
                }
            }
        }
        return reeee;
    }



    public List<String> findValidWords(List<String> dict, char letters[]){

        int []avail = new int[26];
        for(char c : letters){
            int index = c - 'A';
            avail[index]++;
        }
        List<String> result = new ArrayList();
        for(String word: dict){
            int []count = new int[26];
            boolean ok = true;
            for(char c : word.toCharArray()){
                int index = c - 'A';
                count[index]++;

                if(count[index] > avail[index]){
                    ok = false;
                    break;
                }
            }
            if(ok){
                result.add(word);
            }
        }
        return result;
    }
/*
    public static void main(String[] args) {
        AIPlayer play = new AIPlayer("help");


    }*/






}
