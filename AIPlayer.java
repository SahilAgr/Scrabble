import java.util.*;
import java.util.random.*;

public class AIPlayer extends Player{

    private List<String> test;
    Dictionary dictionary;
    Player player;
    Board board;
    LetterBag letters;
    Random rand;


    static final int SIZE = 26;


    public AIPlayer(String name){
        super(name);
        this.rand = new Random();
        this.dictionary = new Dictionary();
        this.player = new Player("Help");
        this.board = new Board();
        this.letters = new LetterBag();

        player.addLettersToHand(letters.getRandomLetters(7));

        String testWord = createLetterSet(player.getHand());
        char[] testing = testWord.toCharArray();
        System.out.println(findValidWords(dictionary.allWords(),testing));


    }

    public void playTurn(Game game, Board board, ArrayList<Tile> hand, Player player){
        for(int i = 0; i< 15;i++){
            for(int j = 0; j < 15; j++){
                Coordinates coord = new Coordinates(Coordinates.xCoordinate.ordinalToXCoordinate(i),Coordinates.yCoordinate.ordinalToYCoordinate(j));
                if(wordOnBoard(board) == ""){
                    //place in the middle of the tile - this is for first turn
                }else{
                    String playerLetters = createLetterSet(player.getHand());
                    char [] playerLetterArray = playerLetters.toCharArray();
                    List<String> allPossibleWords = findValidWords(dictionary.allWords(),playerLetterArray);
                    String wordToPlace = allPossibleWords.get(rand.nextInt(allPossibleWords.size()));
                    int k=0;
                    for(k = 0; k < wordToPlace.length();k++){
                        if(wordOnBoard(board).charAt(0) == wordToPlace.charAt(k)){
                            break;
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
        for(int i = 0; i< 15;i++){
            for(int j = 0; j < 15; j++){
                Coordinates coord = new Coordinates(Coordinates.xCoordinate.ordinalToXCoordinate(i),Coordinates.yCoordinate.ordinalToYCoordinate(j));
                if(!board.checkFree(coord)){
                    return board.getLetter(coord);
                }else {
                    return"";
                }
            }
        }
        return "";
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

    public static void main(String[] args) {
        AIPlayer play = new AIPlayer("help");


    }






}
