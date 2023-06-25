package code.first.exercise.footballBettingSystem;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent extends NamedBaseEntity{

    @OneToMany(mappedBy = "continent")
    private Set<Country> countries;


    public Continent() {
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
