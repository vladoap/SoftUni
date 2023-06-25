package code.first.exercise.footballBettingSystem;


import javax.persistence.*;

@Entity
@Table(name = "competitions")
public class Competition extends NamedBaseEntity{

    @Enumerated(value = EnumType.STRING)
    private CompetitionType competitionType;

    public Competition() {
    }


    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }
}
