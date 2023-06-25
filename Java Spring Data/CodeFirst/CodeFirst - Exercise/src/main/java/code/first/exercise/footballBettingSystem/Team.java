package code.first.exercise.footballBettingSystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends NamedBaseEntity{

    @Column
    private Byte[] logo;

    @Column(name = "initials", length = 3)
    private String letterInitials;

    @ManyToOne
    private Color primaryColor;

    @ManyToOne
    private Color secondaryColor;

    @ManyToOne
    private Town town;

    @Column
    private Float budget;

    public Team() {
    }

    public Byte[] getLogo() {
        return logo;
    }

    public void setLogo(Byte[] logo) {
        this.logo = logo;
    }

    public String getLetterInitials() {
        return letterInitials;
    }

    public void setLetterInitials(String letterInitials) {
        this.letterInitials = letterInitials;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }
}
