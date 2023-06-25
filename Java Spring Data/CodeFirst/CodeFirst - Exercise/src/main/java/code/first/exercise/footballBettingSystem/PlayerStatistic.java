package code.first.exercise.footballBettingSystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "player_statistics")
@IdClass(PlayerStatisticId.class)
public class PlayerStatistic {


    @Id
    @OneToOne
    private Game game;

    @Id
    @OneToOne
    private Player player;


    @Column(name = "scored_goals")
    private Integer scoredGoals;

    @Column(name = "player_assists")
    private Integer playerAssists;

    @Column(name = "played_minutes")
    private Short playedMinutes;

    public PlayerStatistic() {
    }



    public Integer getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(Integer scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public Integer getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(Integer playerAssists) {
        this.playerAssists = playerAssists;
    }

    public Short getPlayedMinutes() {
        return playedMinutes;
    }

    public void setPlayedMinutes(Short playedMinutes) {
        this.playedMinutes = playedMinutes;
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

