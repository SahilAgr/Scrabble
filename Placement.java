public class Placement {
    private boolean isLegalPlace;
    private String errorMessage;

    private int score;
    public Placement(boolean isLegalPlace, String errorMessage, int score) {
        this.isLegalPlace = isLegalPlace;
        this.errorMessage = errorMessage;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
    public boolean isLegalPlace() {
        return isLegalPlace;
    }

    public void setLegalPlace(boolean isLegalPlace) {
        this.isLegalPlace = isLegalPlace;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
