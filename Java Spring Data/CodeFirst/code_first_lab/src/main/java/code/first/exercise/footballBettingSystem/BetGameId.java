package code.first.exercise.footballBettingSystem;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class BetGameId implements Serializable {

    @OneToOne
    private Game game;

    @OneToOne
    private Bet bet;

    public BetGameId() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }
}
