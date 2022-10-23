import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private Integer score;
    private ArrayList<letter> Hand;
    private HashMap<letter, Integer> letterScores;
    
    private enum letter{
        A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
    }
    
    public Player(){
        letterScores = new HashMap();
        createLetterScores();
        score = 0;
    }


    //Yes, it's disguisting.
    private void createLetterScores() {
        letterScores.put(letter.A, 1);
        letterScores.put(letter.B, 3);
        letterScores.put(letter.C, 3);
        letterScores.put(letter.D, 2);
        letterScores.put(letter.E, 1);
        letterScores.put(letter.F, 4);
        letterScores.put(letter.G, 2);
        letterScores.put(letter.H, 4);
        letterScores.put(letter.I, 1);
        letterScores.put(letter.J, 8);
        letterScores.put(letter.K, 5);
        letterScores.put(letter.L, 1);
        letterScores.put(letter.M, 3);
        letterScores.put(letter.N, 1);
        letterScores.put(letter.O, 1);
        letterScores.put(letter.P, 3);
        letterScores.put(letter.Q, 10);
        letterScores.put(letter.R, 1);
        letterScores.put(letter.S, 1);
        letterScores.put(letter.T, 1);
        letterScores.put(letter.U, 1);
        letterScores.put(letter.V, 4);
        letterScores.put(letter.W, 4);
        letterScores.put(letter.X, 8);
        letterScores.put(letter.Y, 4);
        letterScores.put(letter.Z, 10);
    }
}
