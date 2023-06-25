package code.first.exercise.footballBettingSystem;

import code.first.exercise.sales.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "colors")
public class Color extends NamedBaseEntity {

    public Color() {
    }

}
