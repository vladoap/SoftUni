package code.first.exercise.hospital;

import code.first.exercise.sales.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "GPs")
public class GP extends BaseEntity {

    @OneToMany(mappedBy = "gp")
    private Set<Patient> patients;

    public GP() {
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
