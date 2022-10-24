import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
public class LetterBag {
    public enum Letter{
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
    private List<Letter> letterBag;
    
    public LetterBag(){
        //its also disguisting. I probably should just use a json. i will do this tomorrow.
        letterBag = new ArrayList<>();
        for(int i = 0;i < 9;i++){
            letterBag.add(Letter.A);
        }
        for(int i = 0;i < 2;i++){
            letterBag.add(Letter.B);
        }
        for(int i = 0;i < 3;i++){
            letterBag.add(Letter.C);
        }
        for(int i = 0;i < 4;i++){
            letterBag.add(Letter.D);
        }
        for(int i = 0;i < 12;i++){
            letterBag.add(Letter.E);
        }
        for(int i = 0;i < 2;i++){
            letterBag.add(Letter.F);
        }
        for(int i = 0;i < 3;i++){
            letterBag.add(Letter.G);
        }
        for(int i = 0;i < 2;i++){
            letterBag.add(Letter.H);
        }
        for(int i = 0;i < 9;i++){
            letterBag.add(Letter.I);
        }
        letterBag.add(Letter.J);
        letterBag.add(Letter.K);
        for(int i = 0;i < 4;i++){
            letterBag.add(Letter.L);
        }
        for(int i = 0;i < 2;i++){
            letterBag.add(Letter.M);
        }
        for(int i = 0;i < 6;i++){
            letterBag.add(Letter.N);
        }
        for(int i = 0;i < 8;i++){
            letterBag.add(Letter.O);
        }
        for(int i = 0;i < 2;i++){
            letterBag.add(Letter.P);
        } 
        letterBag.add(Letter.Q);
        for(int i = 0;i < 6;i++){
            letterBag.add(Letter.R);
        }
        for(int i = 0;i < 4;i++){
            letterBag.add(Letter.S);
        }
        for(int i = 0;i < 6;i++){
            letterBag.add(Letter.T);
        }
        for(int i = 0;i < 4;i++){
            letterBag.add(Letter.U);
        }
        for(int i = 0;i < 2;i++){
            letterBag.add(Letter.V);
        }
        for(int i = 0;i < 2;i++){
            letterBag.add(Letter.W);
        }
        letterBag.add(Letter.X);
        for(int i = 0;i < 2;i++){
            letterBag.add(Letter.Y);
        }
        letterBag.add(Letter.Z);
    }
    public ArrayList<Letter> getRandomLetters(Integer amount){
        ArrayList<Letter> randomLetters = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < amount;i++){
            randomLetters.add(letterBag.remove(random.nextInt(letterBag.size()-1)));
        }
        return randomLetters;
    }
    public List<Letter> getLetters(){
        return letterBag;
    } 


    public static void main(String[] args){
        LetterBag letterBag = new LetterBag();
        System.out.println(letterBag.getRandomLetters(7));
        System.out.println("\n\n");
        System.out.println(letterBag.getLetters());
    }
}
