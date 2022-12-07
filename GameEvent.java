import java.util.EventObject;

public class GameEvent extends EventObject implements Serializable{

    public static final long serialVersionUID = 1L;
    private Placement place;
    private Board board;
    public Placement getPlace() {
        return place;
    }

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public Board getBoard(){
        return board;
    }

    public GameEvent(Game game, Placement place, Player currPlayer, Board board) {
        super(game);
        this.place = place;
        this.player = currPlayer;
        this.board = board;
    }
    
}
