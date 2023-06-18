package entities;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;

import java.time.LocalDate;

@Entity(name = "departments")
public class Department {

    @Id
    private long id;

    @Column(name = "department")
    private String name;

    @Column(name = "working_people")
    private int countPeople;



    public Department() {}

    public Department(long id, String name, int countPeople) {
        this.id = id;
        this.name = name;
        this.countPeople = countPeople;
    }
}
