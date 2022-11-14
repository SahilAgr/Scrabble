import java.util.ArrayList;

public interface ScrabbleView {
    void update(GameEvent event);

    void gameOver(ArrayList<Player> players);
}