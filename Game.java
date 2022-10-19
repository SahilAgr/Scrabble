import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private Dictionary dictionary;
    private Board board;
    private Parser parser;
    private Map<letter,Integer> letterScores;

    //hardcoding the letters so we dont get reliant on strings...
    //not that a single letter is likely to create a typo... but hey
    private enum letter{
        A,B,C,D,E,F,G,H,I,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
    }

    public Game(){
        /**create letterbag with setup peices in place (xAs, yBs, etc)
        foreach letter in letterbag, assign a score.
        im halfassed designing while im tired, so im not sure where
        the most benifical placement of the enum letters is.
        (probably in letterbag TBH.)
        **/      
    }   
}