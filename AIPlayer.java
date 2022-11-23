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
                List<Tile> playerLetterArray = player.getHand();
                if (board.getLetter(coord).length() == 1){
                    playerLetterArray.add(board.getTile(coord));
                }
                char [] something = new char[playerLetterArray.size()];
                for(int k = 0; k < playerLetterArray.size(); k++){
                    something[k] =playerLetterArray.get(i).getString().charAt(0);
                }
                String what =  createLetterSet(player.getHand());
                System.out.println(what.toCharArray());
                ArrayList<String> allPossibleWords =findValidWords(dictionary.allWords(), what.toCharArray());
                //stack overflow told me its not good to make a nested dictionary.
                FakeList xD = new FakeList(allPossibleWords);
                possibleWordsAndCoordinates.put(coord, xD);
                
            }
        }
        if(possibleWordsAndCoordinates.keySet().size() == 0){
            Coordinates base = new Coordinates(Coordinates.xCoordinate.H,8);
            List<String> defaultWord = findValidWords(dictionary.allWords(),player.getHand().toString().toCharArray());
            game.place("right",base,defaultWord.get(0),false);
        }
        for(Coordinates c: possibleWordsAndCoordinates.keySet()){
            ArrayList<String> thing = search((ArrayList<String>) possibleWordsAndCoordinates.get(c).getAaaaa(),board.getTile(c).getString().charAt(0));
            for(String s : thing){

                if(board.checkPlacement(c,s,"right",true, player).isLegalPlace()){
                    System.out.println("hello right");
                    game.place("right",c,s,false);
                    return;
                } else if (board.checkPlacement(c,s,"down",true, player).isLegalPlace()) {
                    System.out.println("hello left");
                    game.place("down",c,s,false);
                    return;
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
            //System.out.println(temp);
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

