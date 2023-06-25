package code.first.exercise.footballBettingSystem;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import java.io.Serializable;


public class PlayerStatisticId implements Serializable {


    private Game game;
    private Player player;

    public PlayerStatisticId() {
    }

    public PlayerStatisticId(Game game, Player player) {
        this.game = game;
        this.player = player;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
