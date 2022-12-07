import java.util.*;
import java.io.Serializable;

public class AIPlayer extends Player implements Serializable{

    //private List<String> test;

    private static final String RIGHT = "right";
    private static final String ASTERISK = "*";
    private static final String DOWN = "down";
    private Dictionary dictionary;
    private Board board;
    private Random rand;
    public static final long serialVersionUID = 1L;


    Placement placement;

    static final int SIZE = 26;


    public AIPlayer(String name){
        super(name);
        this.rand = new Random();
        this.dictionary = new Dictionary();
        this.board = new Board();

       /* String testWord = createLetterSet(player.getHand());
        char[] testing = testWord.toCharArray();
        System.out.println(findValidWords(dictionary.allWords(),testing));
*/


    }

    public void playTurn(Game game, Board board, Player p){
        ArrayList<Tile> playerLetterArray = this.getHand();
        for(Tile t: playerLetterArray){
            if (t.getString().charAt(0) == '*'){
                game.shuffleHand(ASTERISK);
                return;
            }
        }
        HashMap<Coordinates, FakeList> possibleWordsAndCoordinates = new HashMap<>();
        for(int i = 0; i< 15;i++){
            for(int j = 0; j < 15; j++){
                Coordinates coord = new Coordinates(i,j);
                if (! board.checkFree(coord) ){
                    String what = "";
                    for(Tile t: this.getHand()){
                        what += t.getString();
                    }
                    what += board.getTile(coord).getString();
                    System.out.println(what);
                    ArrayList<String> allPossibleWords =findValidWords(dictionary.allWords(), what.toCharArray());
                    allPossibleWords = search(allPossibleWords, board.getTile(coord).getString().charAt(0));
                    //stack overflow told me its not good to make a nested dictionary.
                    FakeList xD = new FakeList(allPossibleWords);
                    possibleWordsAndCoordinates.put(coord, xD);
                }   
            }
        }
        for(Coordinates c: possibleWordsAndCoordinates.keySet()){
            System.out.println(c.getXCoordinate().toString() + " " + c.getYCoordinate().toString());
            for(String s: possibleWordsAndCoordinates.get(c).getAaaaa()){
                System.out.println(s);
            }
        }
        if(possibleWordsAndCoordinates.keySet().size() == 0){
            System.out.println("after keyset.size()==0");
            Coordinates base = new Coordinates(Coordinates.xCoordinate.H,8);
            List<String> defaultWord = findValidWords(dictionary.allWords(),this.getHand().toString().toCharArray());
            game.place("right",base,defaultWord.get(rand.nextInt(defaultWord.size()-1)),false);
            playerLetterArray = this.getHand();
            return;
        }
        final String NO_POSSIBLE = "NO_POSSIBLE";
        Placement bestPossible = new Placement(true, NO_POSSIBLE,0);
        Coordinates coords = new Coordinates(7,7);
        String word = NO_POSSIBLE;
        String direction = NO_POSSIBLE;
        for(Coordinates c: possibleWordsAndCoordinates.keySet()){
            for(String s : possibleWordsAndCoordinates.get(c).getAaaaa()){
                Placement testRight = board.checkPlacement(c,s,"right",true, this);
                Placement testDown = board.checkPlacement(c,s,"down",true, this);
                if(testRight.isLegalPlace()){
                    if (testRight.getScore() > bestPossible.getScore()){
                        bestPossible = testRight;
                        word = s;
                        coords = c;
                        direction = RIGHT;
                    }
                } else if (testDown.isLegalPlace()) {
                    if(testDown.getScore() > bestPossible.getScore()){
                        bestPossible = testRight;
                        word = s;
                        coords = c;
                        direction = DOWN;
                    }
                }
            }
        }
        if(bestPossible.getErrorMessage().equals(NO_POSSIBLE)){
            game.shuffleHand("");
        } else {
            game.place(direction,coords,word,false);

        }

    }



    public ArrayList<String> findValidWords(List<String> dict, char letters[]){
        for(int i = 0; i <letters.length;i++){
            letters[i] = Character.toUpperCase(letters[i]);
        }
        int []avail = new int[26];
        for(char c : letters){
            int index = c - 'A';
            avail[index]++;
        }
        ArrayList<String> result = new ArrayList<>();
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


    public ArrayList<String> search(ArrayList<String> possible,char c){
        ArrayList<String> finalBoss = new ArrayList<>();
        for (String s: possible){
            if(s.charAt(0) == c){
                finalBoss.add(s);
            }
        }
        return finalBoss;
    }
/*
    public static void main(String[] args) {
        AIPlayer play = new AIPlayer("help");


    }*/


    //this is litterally just so i can put a arraylist in a hashmap.
    private class FakeList{
        private ArrayList<String> aaaaa;
        FakeList(ArrayList<String> aaa){
            aaaaa = aaa;
        }
        public List<String> getAaaaa() {
            return aaaaa;
        }

    }





}

