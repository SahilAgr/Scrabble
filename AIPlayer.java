import java.util.*;

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
        String playerLetters = createLetterSet(player.getHand());
        HashMap<Coordinates, FakeList> possibleWordsAndCoordinates = new HashMap<>();
        for(int i = 0; i< 15;i++){
            for(int j = 0; j < 15; j++){
                Coordinates coord = new Coordinates(i,j);
                char [] playerLetterArray = new char [8];
                playerLetterArray = playerLetters.toCharArray();
                if (board.getLetter(coord).length() == 1){
                    playerLetterArray[8] = board.getLetter(coord).charAt(0);
                }
                ArrayList<String> allPossibleWords = findValidWords(dictionary.allWords(), playerLetterArray);
                //stack overflow told me its not good to make a nested dictionary.
                FakeList xD = new FakeList(allPossibleWords);
                possibleWordsAndCoordinates.put(coord, xD);
                
            }
        }
        for(Coordinates c: possibleWordsAndCoordinates.keySet()){
            for(String s : possibleWordsAndCoordinates.get(c).getAaaaa()){
                //its... beautiful.
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



    public ArrayList<String> findValidWords(List<String> dict, char letters[]){

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
/*
    public static void main(String[] args) {
        AIPlayer play = new AIPlayer("help");


    }*/


    //this is litterally just so i can put a arraylist in a hashmap.
    private class FakeList{
        private List<String> aaaaa;
        FakeList(ArrayList<String> aaa){
            aaaaa = aaa;
        }
        public List<String> getAaaaa() {
            return aaaaa;
        }

    }





}

