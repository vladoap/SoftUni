package entities;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;

import java.time.LocalDate;

@Entity(name = "users")
public class User {

    @Id
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private int age;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public User() {}

    public User(String username, int age, LocalDate registrationDate) {
        this.username = username;
        this.age = age;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
