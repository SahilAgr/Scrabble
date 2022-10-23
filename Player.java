import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    
    Integer score;

    private enum Letter{
        A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;

        private HashMap<Letter, Integer> letterScores;
        private Letter(){
            letterScores = new HashMap<>();
            createLetterScores();
        }

        private void createLetterScores() {
            letterScores.put(Letter.A, 1);
            letterScores.put(Letter.B, 3);
            letterScores.put(Letter.C, 3);
            letterScores.put(Letter.D, 2);
            letterScores.put(Letter.E, 1);
            letterScores.put(Letter.F, 4);
            letterScores.put(Letter.G, 2);
            letterScores.put(Letter.H, 4);
            letterScores.put(Letter.I, 1);
            letterScores.put(Letter.J, 8);
            letterScores.put(Letter.K, 5);
            letterScores.put(Letter.L, 1);
            letterScores.put(Letter.M, 3);
            letterScores.put(Letter.N, 1);
            letterScores.put(Letter.O, 1);
            letterScores.put(Letter.P, 3);
            letterScores.put(Letter.Q, 10);
            letterScores.put(Letter.R, 1);
            letterScores.put(Letter.S, 1);
            letterScores.put(Letter.T, 1);
            letterScores.put(Letter.U, 1);
            letterScores.put(Letter.V, 4);
            letterScores.put(Letter.W, 4);
            letterScores.put(Letter.X, 8);
            letterScores.put(Letter.Y, 4);
            letterScores.put(Letter.Z, 10);
        }

        public Integer getLetterScore(Letter letter){
            return letterScores.get(letter);
        }
    }
    
    public Player(){
        score = 0;
    }
}
