package code.first.exercise.footballBettingSystem;

import code.first.exercise.sales.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class NamedBaseEntity extends BaseEntity {

    @Column
    private String name;

    public NamedBaseEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
