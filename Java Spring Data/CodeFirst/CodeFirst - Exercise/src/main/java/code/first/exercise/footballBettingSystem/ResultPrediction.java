package code.first.exercise.footballBettingSystem;

import code.first.exercise.sales.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private Prediction prediction;

    public ResultPrediction() {
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
