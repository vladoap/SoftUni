package code.first.exercise.footballBettingSystem;

import code.first.exercise.sales.BaseEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bets")
public class Bet extends BaseEntity {

    @Column(name = "bet_money")
    private BigInteger betMoney;

    @Column(name = "date_and_time_of_bet")
    private LocalDateTime betDateTime;

    @ManyToOne
    private BettingUser bettingUser;

    @ManyToMany(mappedBy = "bets")
    private Set<Game> games;

    public Bet() {
    }

    public BigInteger getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(BigInteger betMoney) {
        this.betMoney = betMoney;
    }

    public LocalDateTime getBetDateTime() {
        return betDateTime;
    }

    public void setBetDateTime(LocalDateTime betDateTime) {
        this.betDateTime = betDateTime;
    }

    public BettingUser getBettingUser() {
        return bettingUser;
    }

    public void setBettingUser(BettingUser user) {
        this.bettingUser = user;
    }
}
