import java.io.Serializable;

public class Placement implements Serializable{
    private final boolean isLegalPlace;
    private String errorMessage;
    private final int score;

    public static final long serialVersionUID = 1L;


    public Placement(boolean isLegalPlace, String errorMessage, int score) {
        this.isLegalPlace = isLegalPlace;
        this.errorMessage = errorMessage;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    
    public boolean isLegalPlace() {
        return isLegalPlace;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
