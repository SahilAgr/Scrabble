import java.util.EventObject;

public class GameEvent extends EventObject{

    private Placement place;
    public Placement getPlace() {
        return place;
    }

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public GameEvent(Game game, Placement place, Player currPlayer) {
        super(game);
        this.place = place;
        this.player = currPlayer;
    }
    
}
