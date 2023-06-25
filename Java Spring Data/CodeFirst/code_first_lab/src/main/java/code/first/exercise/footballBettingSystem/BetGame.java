package code.first.exercise.footballBettingSystem;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bet_games")
public class BetGame {

    @EmbeddedId
    private BetGameId betGameId;

    @OneToOne
    private ResultPrediction resultPrediction;

    public BetGame() {
    }

    public BetGameId getBetGameId() {
        return betGameId;
    }

    public void setBetGameId(BetGameId betGameId) {
        this.betGameId = betGameId;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
