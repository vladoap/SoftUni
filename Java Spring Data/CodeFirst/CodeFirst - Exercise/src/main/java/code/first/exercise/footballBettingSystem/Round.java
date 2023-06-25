package code.first.exercise.footballBettingSystem;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rounds")
public class Round extends NamedBaseEntity{

    public Round() {
    }
}
